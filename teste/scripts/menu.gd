extends Node2D

var master_vol = AudioServer.get_bus_index("Master")
var mouseOnMenu = false

func _ready():
    $Menu/VSlider.value = Global.sound
    
    $Menu/VSlider.connect("value_changed", Callable(self, "on_VSlider_value_changed"))
    $Menu/ButtonVolume.connect("pressed", Callable(self, "sound"))
    $Menu/ButtonExit.connect("pressed", Callable(self, "exit"))
    
    scene_change()

func _process(_delta):
    if mouseOnMenu:
        var position = get_global_mouse_position()
        $Reader.position = position
        $Reader.position.x += 60
        $Reader.position.y += 10
    else:
        $Reader.position = Vector2(-10, -10)

func on_VSlider_value_changed(value):
    AudioServer.set_bus_volume_db(master_vol , value)
    if value == -20:
        Global.isSoundOn = false
        AudioServer.set_bus_mute(master_vol , true)
        $Menu/ButtonVolume/VolumeOff.visible = true
        $Menu/ButtonVolume/VolumeOn.visible = false
    else:
        Global.isSoundOn = true
        Global.som = value
        AudioServer.set_bus_mute(master_vol , false)
        $Menu/ButtonVolume/VolumeOn.visible = true
        $Menu/ButtonVolume/VolumeOff.visible = false

func sound():
    if Global.isSoundOn == true && Global.som != -20:
        $Menu/ButtonVolume/VolumeOff.visible = true
        $Menu/VSlider.value = -20
        Global.isSoundOn = false 
    else:
        $Menu/ButtonVolume/VolumeOff.visible = false
        $Menu/VSlider.value = Global.som
        Global.isSoundOn = true

func _on_button_volume_mouse_entered():
    mouseOnMenu = true
    $Reader/text.text = "VOLUME"
    $FixedMenuSound.visible = true
    $BackgroundMenu.visible = false
func _on_button_volume_mouse_exited():
    mouseOnMenu = false
    $FixedMenuSound.visible = false
    $BackgroundMenu.visible = true

func exit():
    if Global.isInitialScreen == true:
        var code = """
            window.parent.postMessage({ type: 'closeGame' }, '*');
        """
        JavaScriptBridge.eval(code)
        get_tree().quit()
    else:
        get_tree().change_scene_to_file("res://scenes/InitialScreen.tscn")

func _on_button_exit_mouse_entered():
    mouseOnMenu = true
    $Reader/text.text = "VOLTAR"
    $FixedMenuExit.visible = true
    $BackgroundMenu.visible = false
    if Global.isInitialScreen == true:
        $Reader/text.text = "SAIR"
        $Menu/ButtonExit/OpenDoor.visible = true
        $Menu/ButtonExit/ClosedDoor.visible = false

func _on_button_exit_mouse_exited():
    mouseOnMenu = false
    $FixedMenuExit.visible = false
    $BackgroundMenu.visible = true
    if Global.isInitialScreen == true:
        $Menu/ButtonExit/OpenDoor.visible = false
        $Menu/ButtonExit/ClosedDoor.visible = true

func _on_foraDoInicio_trocarIconMenu():
    $Menu/ButtonExit/OpenDoor.visible = false
    $Menu/ButtonExit/ClosedDoor.visible = false
    $Menu/ButtonExit/GoBack.visible = true

func _on_Inicio_Menu():
    $Menu/ButtonExit/OpenDoor.visible = false
    $Menu/ButtonExit/ClosedDoor.visible = true
    $Menu/ButtonExit/GoBack.visible = false

func scene_change():
    if Global.isSoundOn == true:
        $Menu/ButtonVolume/VolumeOff.visible = false
        $Menu/ButtonVolume/VolumeOn.visible = true
    else:
        $Menu/ButtonVolume/VolumeOff.visible = true
        $Menu/ButtonVolume/VolumeOn.visible = false
        $Menu/VSlider.value = -20
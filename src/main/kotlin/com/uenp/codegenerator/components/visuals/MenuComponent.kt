package com.uenp.codegenerator.components.visuals

import com.uenp.codegenerator.components.interfaces.VisualComponent
import com.uenp.codegenerator.utils.dollarSign

class MenuComponent : VisualComponent {

    override fun generateScript(): String {
        return """
            extends Node2D

            var master_vol = AudioServer.get_bus_index("Master")
            var mouseOnMenu = false
            
            func _ready():
                ${dollarSign}Menu/VSlider.value = Global.sound
                
                ${dollarSign}Menu/VSlider.connect("value_changed", Callable(self, "on_VSlider_value_changed"))
                ${dollarSign}Menu/ButtonVolume.connect("pressed", Callable(self, "sound"))
                ${dollarSign}Menu/ButtonExit.connect("pressed", Callable(self, "exit"))
                
                scene_change()
            
            func _process(_delta):
                if mouseOnMenu:
                    var position = get_global_mouse_position()
                    ${dollarSign}Reader.position = position
                    ${dollarSign}Reader.position.x += 60
                    ${dollarSign}Reader.position.y += 10
                else:
                    ${dollarSign}Reader.position = Vector2(-10, -10)
            
            func on_VSlider_value_changed(value):
                AudioServer.set_bus_volume_db(master_vol , value)
                if value == -20:
                    Global.isSoundOn = false
                    AudioServer.set_bus_mute(master_vol , true)
                    ${dollarSign}Menu/ButtonVolume/VolumeOff.visible = true
                    ${dollarSign}Menu/ButtonVolume/VolumeOn.visible = false
                else:
                    Global.isSoundOn = true
                    Global.som = value
                    AudioServer.set_bus_mute(master_vol , false)
                    ${dollarSign}Menu/ButtonVolume/VolumeOn.visible = true
                    ${dollarSign}Menu/ButtonVolume/VolumeOff.visible = false
            
            func sound():
                if Global.isSoundOn == true && Global.som != -20:
                    ${dollarSign}Menu/ButtonVolume/VolumeOff.visible = true
                    ${dollarSign}Menu/VSlider.value = -20
                    Global.isSoundOn = false 
                else:
                    ${dollarSign}Menu/ButtonVolume/VolumeOff.visible = false
                    ${dollarSign}Menu/VSlider.value = Global.som
                    Global.isSoundOn = true
            
            func _on_button_volume_mouse_entered():
                mouseOnMenu = true
                ${dollarSign}Reader/text.text = "VOLUME"
                ${dollarSign}FixedMenuSound.visible = true
                ${dollarSign}BackgroundMenu.visible = false
            func _on_button_volume_mouse_exited():
                mouseOnMenu = false
                ${dollarSign}FixedMenuSound.visible = false
                ${dollarSign}BackgroundMenu.visible = true
            
            func exit():
                if Global.isInitialScreen == true:
                    var code = ""${'"'}
                        window.parent.postMessage({ type: 'closeGame' }, '*');
                    ""${'"'}
                    JavaScriptBridge.eval(code)
                    get_tree().quit()
                else:
                    get_tree().change_scene_to_file("res://scenes/InitialScreen.tscn")
            
            func _on_button_exit_mouse_entered():
                mouseOnMenu = true
                ${dollarSign}Reader/text.text = "VOLTAR"
                ${dollarSign}FixedMenuExit.visible = true
                ${dollarSign}BackgroundMenu.visible = false
                if Global.isInitialScreen == true:
                    ${dollarSign}Reader/text.text = "SAIR"
                    ${dollarSign}Menu/ButtonExit/OpenDoor.visible = true
                    ${dollarSign}Menu/ButtonExit/ClosedDoor.visible = false
            
            func _on_button_exit_mouse_exited():
                mouseOnMenu = false
                ${dollarSign}FixedMenuExit.visible = false
                ${dollarSign}BackgroundMenu.visible = true
                if Global.isInitialScreen == true:
                    ${dollarSign}Menu/ButtonExit/OpenDoor.visible = false
                    ${dollarSign}Menu/ButtonExit/ClosedDoor.visible = true
            
            func _on_foraDoInicio_trocarIconMenu():
                ${dollarSign}Menu/ButtonExit/OpenDoor.visible = false
                ${dollarSign}Menu/ButtonExit/ClosedDoor.visible = false
                ${dollarSign}Menu/ButtonExit/GoBack.visible = true
            
            func _on_Inicio_Menu():
                ${dollarSign}Menu/ButtonExit/OpenDoor.visible = false
                ${dollarSign}Menu/ButtonExit/ClosedDoor.visible = true
                ${dollarSign}Menu/ButtonExit/GoBack.visible = false
            
            func scene_change():
                if Global.isSoundOn == true:
                    ${dollarSign}Menu/ButtonVolume/VolumeOff.visible = false
                    ${dollarSign}Menu/ButtonVolume/VolumeOn.visible = true
                else:
                    ${dollarSign}Menu/ButtonVolume/VolumeOff.visible = true
                    ${dollarSign}Menu/ButtonVolume/VolumeOn.visible = false
                    ${dollarSign}Menu/VSlider.value = -20
        """.trimIndent()
    }

    override fun generateScene(): String {
        return """
            [gd_scene load_steps=17 format=3 uid="uid://6ysea8wd0byr"]

            [ext_resource type="Texture2D" uid="uid://7x6fj0d6ttjd" path="res://import/menu/volume.png" id="1"]
            [ext_resource type="Texture2D" uid="uid://bvyq8fcmewo8" path="res://import/menu/closed_door.png" id="2"]
            [ext_resource type="Texture2D" uid="uid://0v6ow4w0xr0d" path="res://import/menu/slider_volume_button.png" id="3"]
            [ext_resource type="Script" path="res://scripts/menu.gd" id="4"]
            [ext_resource type="Texture2D" uid="uid://2o1d0aobgyw" path="res://import/menu/slider_volume_bar.png" id="5"]
            [ext_resource type="Texture2D" uid="uid://ct8jyh7byka2f" path="res://import/menu/open_door.png" id="6"]
            [ext_resource type="Texture2D" uid="uid://bwnsea54thr13" path="res://import/menu/muted.png" id="7"]
            [ext_resource type="Texture2D" uid="uid://b4vx3if3sg1wr" path="res://import/menu/fixed_menu.png" id="8"]
            [ext_resource type="Texture2D" uid="uid://nxuyngdrcxdy" path="res://import/menu/fixed_menu_door.png" id="9"]
            [ext_resource type="Texture2D" uid="uid://2f5rup8j22e1" path="res://import/menu/arrow_back.png" id="10"]
            [ext_resource type="Texture2D" uid="uid://cxq35ap44crom" path="res://import/menu/fixed_menu_sound.png" id="12"]
            [ext_resource type="Texture2D" uid="uid://df8e6nnmclxsy" path="res://import/menu/reader.png" id="12_saxmg"]
            [ext_resource type="FontFile" uid="uid://dlcs0n62teb43" path="res://import/fonts/identidad/Identidad-ExtraBold.otf" id="13_1kdnj"]
            
            [sub_resource type="StyleBoxTexture" id="StyleBoxTexture_qtuhb"]
            texture = ExtResource("5")
            texture_margin_left = 39.0
            texture_margin_bottom = 301.0
            
            [sub_resource type="StyleBoxTexture" id="StyleBoxTexture_jjynp"]
            texture = ExtResource("5")
            texture_margin_left = 39.0
            texture_margin_bottom = 301.0
            
            [sub_resource type="StyleBoxTexture" id="StyleBoxTexture_ni4xl"]
            texture = ExtResource("5")
            texture_margin_left = 31.0
            texture_margin_bottom = 301.0
            
            [node name="Menu" type="Node2D"]
            script = ExtResource("4")
            
            [node name="FixedMenuExit" type="Sprite2D" parent="."]
            visible = false
            position = Vector2(48, 331.5)
            scale = Vector2(1, 0.996154)
            texture = ExtResource("9")
            
            [node name="FixedMenuSound" type="Sprite2D" parent="."]
            visible = false
            position = Vector2(46.875, 338.227)
            scale = Vector2(0.992268, 0.983382)
            texture = ExtResource("12")
            
            [node name="BackgroundMenu" type="Sprite2D" parent="."]
            position = Vector2(35, 330)
            texture = ExtResource("8")
            
            [node name="Menu" type="Sprite2D" parent="."]
            
            [node name="ButtonVolume" type="Button" parent="Menu"]
            offset_left = 4.0
            offset_top = 263.0
            offset_right = 62.0
            offset_bottom = 322.0
            focus_mode = 0
            flat = true
            
            [node name="VolumeOn" type="Sprite2D" parent="Menu/ButtonVolume"]
            position = Vector2(29, 30)
            texture = ExtResource("1")
            
            [node name="VolumeOff" type="Sprite2D" parent="Menu/ButtonVolume"]
            visible = false
            position = Vector2(29, 31)
            texture = ExtResource("7")
            
            [node name="ButtonExit" type="Button" parent="Menu"]
            offset_left = 5.0
            offset_top = 370.0
            offset_right = 61.0
            offset_bottom = 432.0
            focus_mode = 0
            flat = true
            
            [node name="ClosedDoor" type="Sprite2D" parent="Menu/ButtonExit"]
            position = Vector2(28, 31)
            texture = ExtResource("2")
            
            [node name="OpenDoor" type="Sprite2D" parent="Menu/ButtonExit"]
            visible = false
            position = Vector2(29, 30)
            texture = ExtResource("6")
            
            [node name="GoBack" type="Sprite2D" parent="Menu/ButtonExit"]
            visible = false
            position = Vector2(33, 37)
            texture = ExtResource("10")
            
            [node name="VSlider" type="VSlider" parent="Menu"]
            offset_left = 75.0
            offset_top = 181.0
            offset_right = 116.0
            offset_bottom = 482.0
            focus_mode = 0
            theme_override_icons/grabber = ExtResource("3")
            theme_override_icons/grabber_highlight = ExtResource("3")
            theme_override_icons/grabber_disabled = ExtResource("3")
            theme_override_styles/slider = SubResource("StyleBoxTexture_qtuhb")
            theme_override_styles/grabber_area = SubResource("StyleBoxTexture_jjynp")
            theme_override_styles/grabber_area_highlight = SubResource("StyleBoxTexture_ni4xl")
            min_value = -20.0
            max_value = 5.0
            step = 0.0
            
            [node name="HTTPRequest" type="HTTPRequest" parent="."]
            
            [node name="Reader" type="Sprite2D" parent="."]
            position = Vector2(-97, 500)
            scale = Vector2(1.08, 1)
            texture = ExtResource("12_saxmg")
            
            [node name="text" type="Label" parent="Reader"]
            offset_left = -44.4445
            offset_top = -16.0
            offset_right = 51.5555
            offset_bottom = 12.0
            theme_override_colors/font_color = Color(0, 0, 0, 1)
            theme_override_fonts/font = ExtResource("13_1kdnj")
            theme_override_font_sizes/font_size = 20
            text = "VOLUME"
            
            [connection signal="mouse_entered" from="Menu/ButtonVolume" to="." method="_on_button_volume_mouse_entered"]
            [connection signal="mouse_exited" from="Menu/ButtonVolume" to="." method="_on_button_volume_mouse_exited"]
            [connection signal="mouse_entered" from="Menu/ButtonExit" to="." method="_on_button_exit_mouse_entered"]
            [connection signal="mouse_exited" from="Menu/ButtonExit" to="." method="_on_button_exit_mouse_exited"]

        """.trimIndent()
    }
}
extends Node2D

func _ready():
	play_intro()
	
	Global.tutorial = 0
	Global.isInitialScreen = true
	
	$start_button.connect("pressed", Callable(self, "_on_start_button_pressed"))
	$how_to_play_button.connect("pressed", Callable(self, "_on_exit_button_pressed"))

func play_intro():
	if Global.playIntro == true:
		Global.playIntro = false
		await get_tree().create_timer(4).timeout
	$Intro.visible = false
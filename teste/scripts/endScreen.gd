extends Node

func _ready() -> void:
	$score.text = "PONTUAÇÃO: " + str(Global.Score)
	$errors.text = "ERROS: " + str(Global.erros)
	Global.isGameConcluded = true
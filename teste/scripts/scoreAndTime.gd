extends Control

var decreasePoints = false

func _ready():
	$Timer.connect("timeout", Callable(self, "_counting_time"))
	$DecreasePoints.connect("timeout", Callable(self, "_decreasing_point"))

func _counting_time():
	$LabelScore.text = str(Global.score)
	
	$Count.text = "Tempo: " + str("%02d"%[Global.gameTimeMin]) + ":" + str("%02d"%[Global.gameTimeSec])
	Global.gameTimeSec += 1 
	if Global.gameTimeSec > 59:
		Global.gameTimeMin += 1
		Global.gameTimeSec = 0
	
	if Global.gameTimeMin == 1 and Global.gameTimeSec > 20 and decreasePoints == false:
		Global.score -= 12
		decreasePoints = true
		$DecreasePoints.start()
	pass


func _decreasing_point():
	Global.Score -= 6
	$DecreasePoints.start()
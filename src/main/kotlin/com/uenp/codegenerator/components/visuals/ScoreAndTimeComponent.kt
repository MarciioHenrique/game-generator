package com.uenp.codegenerator.components.visuals

import com.uenp.codegenerator.components.interfaces.VisualComponent
import com.uenp.codegenerator.utils.dollarSign

class ScoreAndTimeComponent : VisualComponent {
    override fun generateScript(): String {
        return """
            extends Control

            var decreasePoints = false

            func _ready():
            	${dollarSign}Timer.connect("timeout", Callable(self, "_counting_time"))
            	${dollarSign}DecreasePoints.connect("timeout", Callable(self, "_decreasing_point"))

            func _counting_time():
            	${dollarSign}LabelScore.text = str(Global.score)
            	
            	${dollarSign}Count.text = "Tempo: " + str("%02d"%[Global.gameTimeMin]) + ":" + str("%02d"%[Global.gameTimeSec])
            	Global.gameTimeSec += 1 
            	if Global.gameTimeSec > 59:
            		Global.gameTimeMin += 1
            		Global.gameTimeSec = 0
            	
            	if Global.gameTimeMin == 1 and Global.gameTimeSec > 20 and decreasePoints == false:
            		Global.score -= 12
            		decreasePoints = true
            		${dollarSign}DecreasePoints.start()
            	pass


            func _decreasing_point():
            	Global.Score -= 6
            	${dollarSign}DecreasePoints.start()
        """.trimIndent()
    }

    override fun generateScene(): String {
        return """
            [gd_scene load_steps=3 format=3 uid="uid://bixdthne8pbav"]

            [ext_resource type="Script" path="res://scripts/scoreAndTime.gd" id="2"]
            [ext_resource type="FontFile" uid="uid://dlcs0n62teb43" path="res://import/fonts/identidad/Identidad-ExtraBold.otf" id="2_38b21"]

            [node name="ScoreAndTime" type="Control"]
            layout_mode = 3
            anchors_preset = 0
            script = ExtResource("2")

            [node name="Count" type="Label" parent="."]
            layout_mode = 0
            offset_left = 880.0
            offset_top = 32.0
            offset_right = 1002.0
            offset_bottom = 74.0
            theme_override_fonts/font = ExtResource("2_38b21")
            theme_override_font_sizes/font_size = 18
            text = "Tempo: 00:00"
            vertical_alignment = 1

            [node name="LabelScore" type="Label" parent="."]
            layout_mode = 0
            offset_left = 303.0
            offset_top = -111.0
            offset_right = 423.0
            offset_bottom = -69.0
            theme_override_fonts/font = ExtResource("2_38b21")
            theme_override_font_sizes/font_size = 18
            text = "Score: 0"
            horizontal_alignment = 1
            vertical_alignment = 1

            [node name="LabelErrors" type="Label" parent="."]
            layout_mode = 0
            offset_left = 159.0
            offset_top = -107.0
            offset_right = 279.0
            offset_bottom = -65.0
            theme_override_fonts/font = ExtResource("2_38b21")
            theme_override_font_sizes/font_size = 18
            text = "Erros: 0"
            horizontal_alignment = 1
            vertical_alignment = 1

            [node name="Timer" type="Timer" parent="."]
            autostart = true

            [node name="DecreasePoints" type="Timer" parent="."]
            wait_time = 10.0

        """.trimIndent()
    }
}
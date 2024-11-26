package com.uenp.codegenerator.application.components.visuals

import com.uenp.codegenerator.application.components.interfaces.VisualComponent
import com.uenp.codegenerator.utils.dollarSign

class EndScreenComponent : VisualComponent {
    override fun generateScript(): String {
        return """
            extends Node

            func _ready() -> void:
            	${dollarSign}score.text = "PONTUAÇÃO: " + str(Global.Score)
            	${dollarSign}errors.text = "ERROS: " + str(Global.erros)
            	Global.isGameConcluded = true
        """.trimIndent()
    }

    override fun generateScene(): String {
        return """
            [gd_scene load_steps=30 format=3 uid="uid://c751x4vmk1ff6"]

            [ext_resource type="Script" path="res://scripts/endScreen.gd" id="1_c2wms"]
            [ext_resource type="FontFile" uid="uid://bc82x70ugbeoq" path="res://import/fonts/identidad/Identidad-ExtraBold.otf" id="3_7rqnu"]
            
            [node name="endScreen" type="Node"]
            script = ExtResource("1_c2wms")
            
            [node name="score" type="Label" parent="."]
            offset_left = 682.0
            offset_top = 69.0
            offset_right = 951.0
            offset_bottom = 106.0
            theme_override_constants/outline_size = 25
            theme_override_fonts/font = ExtResource("3_7rqnu")
            theme_override_font_sizes/font_size = 30
            text = "PONTUAÇÃO: 100
            "

            [node name="errors" type="Label" parent="."]
            offset_left = 682.0
            offset_top = 125.0
            offset_right = 951.0
            offset_bottom = 162.0
            theme_override_constants/outline_size = 25
            theme_override_fonts/font = ExtResource("3_7rqnu")
            theme_override_font_sizes/font_size = 30
            text = "ERROS: 100
            "
        """.trimIndent()
    }
}
package com.uenp.codegenerator.application.components.visuals

import com.uenp.codegenerator.application.components.interfaces.VisualComponent
import com.uenp.codegenerator.utils.dollarSign

class StartScreenComponent : VisualComponent {
    override fun generateScript(): String {
        return """
            extends Node2D
            
            func _ready():
            	play_intro()
            	
            	Global.tutorial = 0
            	Global.isInitialScreen = true
            	
            	${dollarSign}start_button.connect("pressed", Callable(self, "_on_start_button_pressed"))
            	${dollarSign}how_to_play_button.connect("pressed", Callable(self, "_on_exit_button_pressed"))

            func play_intro():
            	if Global.playIntro == true:
            		Global.playIntro = false
            		await get_tree().create_timer(4).timeout
            	${dollarSign}Intro.visible = false
        """.trimIndent()
    }

    override fun generateScene(): String {
        return """
            [gd_scene load_steps=11 format=3 uid="uid://df8nlojjqb6en"]

            [ext_resource type="Script" path="res://scripts/startScreen.gd" id="2"]
            [ext_resource type="Texture2D" uid="uid://cnlhg0xeux7p5" path="res://import/startScreen/orange_select_button.png" id="4"]
            [ext_resource type="FontFile" uid="uid://bc82x70ugbeoq" path="res://import/fonts/identidad/Identidad-ExtraBold.otf" id="4_mnmc3"]
            [ext_resource type="PackedScene" uid="uid://de2lxijaxgp35" path="res://scenes/menu.tscn" id="6"]
            [ext_resource type="PackedScene" uid="uid://b6hx82fxi15q3" path="res://scenes/intro.tscn" id="7"]
            
            [sub_resource type="StyleBoxTexture" id="StyleBoxTexture_n7x0k"]
            texture = ExtResource("4")
            expand_margin_left = 5.0
            expand_margin_top = 5.0
            expand_margin_right = 5.0
            expand_margin_bottom = 5.0
            
            [sub_resource type="StyleBoxTexture" id="StyleBoxTexture_15t8s"]
            texture = ExtResource("4")
            expand_margin_left = 5.0
            expand_margin_top = 5.0
            expand_margin_right = 5.0
            expand_margin_bottom = 5.0
            
            [sub_resource type="StyleBoxTexture" id="StyleBoxTexture_7dbbs"]
            texture = ExtResource("4")

            [node name="start" type="Node2D"]
            script = ExtResource("2")
            
            [node name="Background" type="Sprite2D" parent="."]
            position = Vector2(512.5, 299.5)
            scale = Vector2(1.00488, 1.01167)
            
            [node name="start_button" type="Button" parent="."]
            offset_left = 230.0
            offset_top = 345.0
            offset_right = 530.0
            offset_bottom = 425.0
            focus_mode = 0
            theme_override_colors/font_outline_color = Color(0, 0, 0, 1)
            theme_override_constants/outline_size = 13
            theme_override_fonts/font = ExtResource("4_mnmc3")
            theme_override_font_sizes/font_size = 35
            theme_override_styles/hover = SubResource("StyleBoxTexture_n7x0k")
            theme_override_styles/pressed = SubResource("StyleBoxTexture_15t8s")
            theme_override_styles/normal = SubResource("StyleBoxTexture_7dbbs")
            text = "JOGAR"
            
            [node name="how_to_play_button" type="Button" parent="."]
            offset_left = 230.0
            offset_top = 460.0
            offset_right = 530.0
            offset_bottom = 540.0
            focus_mode = 0
            theme_override_colors/font_outline_color = Color(0, 0, 0, 1)
            theme_override_constants/outline_size = 13
            theme_override_fonts/font = ExtResource("4_mnmc3")
            theme_override_font_sizes/font_size = 35
            theme_override_styles/hover = SubResource("StyleBoxTexture_n7x0k")
            theme_override_styles/pressed = SubResource("StyleBoxTexture_15t8s")
            theme_override_styles/normal = SubResource("StyleBoxTexture_7dbbs")
            text = "COMO JOGAR"
            
            [node name="Menu" parent="." instance=ExtResource("6")]
            
            [node name="Intro" parent="." instance=ExtResource("7")]
            offset_right = 1024.0
            offset_bottom = 600.0
            grow_horizontal = 2
            grow_vertical = 2

        """.trimIndent()
    }
}
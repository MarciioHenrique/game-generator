package com.uenp.codegenerator.application.components.visuals

import com.uenp.codegenerator.application.components.interfaces.VisualComponent
import com.uenp.codegenerator.utils.dollarSign

class IntroComponent : VisualComponent {
    override fun generateScript(): String {
        return """
            extends VideoStreamPlayer

            func _ready():
	        ${dollarSign}AnimationPlayer.play("FADE OUT")
        """.trimIndent()
    }

    override fun generateScene(): String {
        return """
            [gd_scene load_steps=5 format=3 uid="uid://b6hx82fxi15q3"]

            [ext_resource type="Script" path="res://scripts/intro.gd" id="1"]
            [ext_resource type="VideoStream" path="res://import/videos/intro-ninoedu.ogv" id="1_sq46s"]

            [sub_resource type="Animation" id="1"]
            resource_name = "FADE OUT"
            length = 4.0
            tracks/0/type = "value"
            tracks/0/imported = false
            tracks/0/enabled = true
            tracks/0/path = NodePath(".:modulate")
            tracks/0/interp = 1
            tracks/0/loop_wrap = true
            tracks/0/keys = {
            "times": PackedFloat32Array(3.3, 4),
            "transitions": PackedFloat32Array(1, 1),
            "update": 0,
            "values": [Color(1, 1, 1, 1), Color(1, 1, 1, 0)]
            }

            [sub_resource type="AnimationLibrary" id="AnimationLibrary_6ggpi"]
            _data = {
            "FADE OUT": SubResource("1")
            }

            [node name="VideoStreamPlayer" type="VideoStreamPlayer"]
            anchors_preset = 15
            anchor_right = 1.0
            anchor_bottom = 1.0
            stream = ExtResource("1_sq46s")
            autoplay = true
            expand = true
            script = ExtResource("1")

            [node name="AnimationPlayer" type="AnimationPlayer" parent="."]
            libraries = {
            "": SubResource("AnimationLibrary_6ggpi")
            }

        """.trimIndent()
    }
}
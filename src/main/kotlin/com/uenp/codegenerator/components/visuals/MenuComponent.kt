package com.uenp.codegenerator.components.visuals

import com.uenp.codegenerator.components.interfaces.VisualComponent

class MenuComponent: VisualComponent {
    private val dollarSign = "$"

    override fun generateScript(): String {
        return """
            extends Node2D

            var master_vol = AudioServer.get_bus_index("Master")
            var mouseOnMenu = false
            
            func _ready():
                ${dollarSign}Menu/VSlider.value = Global.som\
                
                ${dollarSign}Menu/VSlider.connect("value_changed", Callable(self, "on_VSlider_value_changed"))
                ${dollarSign}Menu/button_volume.connect("pressed", Callable(self, "sound"))
                ${dollarSign}Menu/button_sair.connect("pressed", Callable(self, "porta"))
                
                mudou_scene()
            
            func _process(_delta):
                if mouseOnMenu:
                    var posicao = get_global_mouse_position()
                    ${dollarSign}Leitor.position = posicao
                    ${dollarSign}Leitor.position.x += 60
                    ${dollarSign}Leitor.position.y += 10
                else:
                    ${dollarSign}Leitor.position = Vector2(-10, -10)
            
            func on_VSlider_value_changed(value):
                AudioServer.set_bus_volume_db(master_vol , value)
                if value == -20:
                    Global.volume_ligado = false
                    AudioServer.set_bus_mute(master_vol , true)
                    ${dollarSign}Menu/button_volume/Volume_OFF.visible = true
                    ${dollarSign}Menu/button_volume/Volume_ON.visible = false
                else:
                    Global.volume_ligado = true
                    Global.som = value
                    AudioServer.set_bus_mute(master_vol , false)
                    ${dollarSign}Menu/button_volume/Volume_ON.visible = true
                    ${dollarSign}Menu/button_volume/Volume_OFF.visible = false
            
            func sound():
                if Global.volume_ligado == true && Global.som != -20:
                    ${dollarSign}Menu/button_volume/Volume_OFF.visible = true
                    ${dollarSign}Menu/VSlider.value = -20
                    Global.volume_ligado = false 
                else:
                    ${dollarSign}Menu/button_volume/Volume_OFF.visible = false
                    ${dollarSign}Menu/VSlider.value = Global.som
                    Global.volume_ligado = true
            
            func _on_button_volume_mouse_entered():
                mouseOnMenu = true
                ${dollarSign}Leitor/texto.text = "VOLUME"
                ${dollarSign}MenuFixoSom.visible = true
                ${dollarSign}FundoMenu.visible = false
            func _on_button_volume_mouse_exited():
                mouseOnMenu = false
                ${dollarSign}MenuFixoSom.visible = false
                ${dollarSign}FundoMenu.visible = true
            
            func porta():
                if Global.telaInicial == true:
                    var code = ""${'"'}
                        window.parent.postMessage({ type: 'closeGame' }, '*');
                    ""${'"'}
                    JavaScriptBridge.eval(code)
                    get_tree().quit()
                else:
                    get_tree().change_scene_to_file("res://scenes/TelaDeInicio.tscn")
            
            func _on_button_sair_mouse_entered():
                mouseOnMenu = true
                ${dollarSign}Leitor/texto.text = "VOLTAR"
                ${dollarSign}MenuFixoPorta.visible = true
                ${dollarSign}FundoMenu.visible = false
                if Global.telaInicial == true:
                    ${dollarSign}Leitor/texto.text = "SAIR"
                    ${dollarSign}Menu/button_sair/Aberta.visible = true
                    ${dollarSign}Menu/button_sair/Normal.visible = false
            
            func _on_button_sair_mouse_exited():
                mouseOnMenu = false
                ${dollarSign}MenuFixoPorta.visible = false
                ${dollarSign}FundoMenu.visible = true
                if Global.telaInicial == true:
                    ${dollarSign}Menu/button_sair/Aberta.visible = false
                    ${dollarSign}Menu/button_sair/Normal.visible = true
            
            func _on_foraDoInicio_trocarIconMenu():
                ${dollarSign}Menu/button_sair/Aberta.visible = false
                ${dollarSign}Menu/button_sair/Normal.visible = false
                ${dollarSign}Menu/button_sair/Voltar.visible = true
            
            func _on_Inicio_Menu():
                ${dollarSign}Menu/button_sair/Aberta.visible = false
                ${dollarSign}Menu/button_sair/Normal.visible = true
                ${dollarSign}Menu/button_sair/Voltar.visible = false
            
            func mudou_scene():
                if Global.volume_ligado == true:
                    ${dollarSign}Menu/button_volume/Volume_OFF.visible = false
                    ${dollarSign}Menu/button_volume/Volume_ON.visible = true
                else:
                    ${dollarSign}Menu/button_volume/Volume_OFF.visible = true
                    ${dollarSign}Menu/button_volume/Volume_ON.visible = false
                    ${dollarSign}Menu/VSlider.value = -20
        """.trimIndent()
    }

    override fun generateScene(): String {
        return """
            [gd_scene load_steps=17 format=3 uid="uid://6ysea8wd0byr"]

            [ext_resource type="Texture2D" uid="uid://7x6fj0d6ttjd" path="res://import/Menu/volume.png" id="1"]
            [ext_resource type="Texture2D" uid="uid://bvyq8fcmewo8" path="res://import/Menu/door.png" id="2"]
            [ext_resource type="Texture2D" uid="uid://0v6ow4w0xr0d" path="res://import/Menu/slider_vol2.png" id="3"]
            [ext_resource type="Script" path="res://scripts/Menu.gd" id="4"]
            [ext_resource type="Texture2D" uid="uid://2o1d0aobgyw" path="res://import/Menu/slider_vol.png" id="5"]
            [ext_resource type="Texture2D" uid="uid://ct8jyh7byka2f" path="res://import/Menu/door2.png" id="6"]
            [ext_resource type="Texture2D" uid="uid://bwnsea54thr13" path="res://import/Menu/muted.png" id="7"]
            [ext_resource type="Texture2D" uid="uid://b4vx3if3sg1wr" path="res://import/Menu/menu_fixo.png" id="8"]
            [ext_resource type="Texture2D" uid="uid://nxuyngdrcxdy" path="res://import/Menu/menu_fixo_porta.png" id="9"]
            [ext_resource type="Texture2D" uid="uid://2f5rup8j22e1" path="res://import/Menu/seta_back.png" id="10"]
            [ext_resource type="Texture2D" uid="uid://cxq35ap44crom" path="res://import/Menu/menu_fixo_som.png" id="12"]
            [ext_resource type="Texture2D" uid="uid://df8e6nnmclxsy" path="res://import/Menu/Leitor.png" id="12_saxmg"]
            [ext_resource type="FontFile" uid="uid://dlcs0n62teb43" path="res://import/Fontes/identidad/Identidad-ExtraBold.otf" id="13_1kdnj"]
            
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
            
            [node name="MenuFixoPorta" type="Sprite2D" parent="."]
            visible = false
            position = Vector2(48, 331.5)
            scale = Vector2(1, 0.996154)
            texture = ExtResource("9")
            
            [node name="MenuFixoSom" type="Sprite2D" parent="."]
            visible = false
            position = Vector2(46.875, 338.227)
            scale = Vector2(0.992268, 0.983382)
            texture = ExtResource("12")
            
            [node name="FundoMenu" type="Sprite2D" parent="."]
            position = Vector2(35, 330)
            texture = ExtResource("8")
            
            [node name="Menu" type="Sprite2D" parent="."]
            
            [node name="button_volume" type="Button" parent="Menu"]
            offset_left = 4.0
            offset_top = 263.0
            offset_right = 62.0
            offset_bottom = 322.0
            focus_mode = 0
            flat = true
            
            [node name="Volume_ON" type="Sprite2D" parent="Menu/button_volume"]
            position = Vector2(29, 30)
            texture = ExtResource("1")
            
            [node name="Volume_OFF" type="Sprite2D" parent="Menu/button_volume"]
            visible = false
            position = Vector2(29, 31)
            texture = ExtResource("7")
            
            [node name="button_sair" type="Button" parent="Menu"]
            offset_left = 5.0
            offset_top = 370.0
            offset_right = 61.0
            offset_bottom = 432.0
            focus_mode = 0
            flat = true
            
            [node name="Normal" type="Sprite2D" parent="Menu/button_sair"]
            position = Vector2(28, 31)
            texture = ExtResource("2")
            
            [node name="Aberta" type="Sprite2D" parent="Menu/button_sair"]
            visible = false
            position = Vector2(29, 30)
            texture = ExtResource("6")
            
            [node name="Voltar" type="Sprite2D" parent="Menu/button_sair"]
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
            
            [node name="Leitor" type="Sprite2D" parent="."]
            position = Vector2(-97, 500)
            scale = Vector2(1.08, 1)
            texture = ExtResource("12_saxmg")
            
            [node name="texto" type="Label" parent="Leitor"]
            offset_left = -44.4445
            offset_top = -16.0
            offset_right = 51.5555
            offset_bottom = 12.0
            theme_override_colors/font_color = Color(0, 0, 0, 1)
            theme_override_fonts/font = ExtResource("13_1kdnj")
            theme_override_font_sizes/font_size = 20
            text = "VOLUME"
            
            [connection signal="mouse_entered" from="Menu/button_volume" to="." method="_on_button_volume_mouse_entered"]
            [connection signal="mouse_exited" from="Menu/button_volume" to="." method="_on_button_volume_mouse_exited"]
            [connection signal="mouse_entered" from="Menu/button_sair" to="." method="_on_button_sair_mouse_entered"]
            [connection signal="mouse_exited" from="Menu/button_sair" to="." method="_on_button_sair_mouse_exited"]

        """.trimIndent()
    }
}
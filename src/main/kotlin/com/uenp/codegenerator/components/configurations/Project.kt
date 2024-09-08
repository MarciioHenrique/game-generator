package com.uenp.codegenerator.components.configurations

import com.uenp.codegenerator.components.interfaces.ConfigurationComponent
import com.uenp.codegenerator.controllers.requests.ConfigurationsRequest

class Project : ConfigurationComponent {
    override fun generateScript(configurations: ConfigurationsRequest): String {
        return """
            config_version=5
            
            [application]
            
            config/name="${configurations.projectName}"
            config/features=PackedStringArray("4.3", "GL Compatibility")
            config/icon="res://icon.png"
            
            [display]
            
            window/size/viewport_width=1024
            window/size/viewport_height=600
            window/stretch/mode="canvas_items"
            window/stretch/aspect="ignore"
            window/handheld/orientation="sensor"
            
            [gui]
            
            common/drop_mouse_on_gui_input_disabled=true
            
            [physics]
            
            common/enable_pause_aware_picking=true
            
            [rendering]
            
            renderer/rendering_method="gl_compatibility"
            renderer/rendering_method.mobile="gl_compatibility"
        """.trimIndent()
    }
}
package com.uenp.codegenerator.components.configurations

import com.uenp.codegenerator.components.interfaces.ConfigurationComponent
import com.uenp.codegenerator.controllers.requests.ConfigurationsRequest

class Global : ConfigurationComponent {
    override fun generateScript(configurations: ConfigurationsRequest): String {
        return """
            extends Node2D

            var sound = -7
            var isSoundOn = true
            var volumeOnScreen = true
            
            var errors = 0
            var score = 0
            var gameTimeMin = 0
            var gameTimeSec = 0
            var isGameConcluded = false
        """.trimIndent()
    }
}
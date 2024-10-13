package com.uenp.codegenerator.application.components.interfaces

import com.uenp.codegenerator.controllers.requests.ConfigurationsRequest

interface ConfigurationComponent {
    fun generateScript(configurations: ConfigurationsRequest): String
}
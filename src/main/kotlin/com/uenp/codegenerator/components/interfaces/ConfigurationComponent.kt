package com.uenp.codegenerator.components.interfaces

import com.uenp.codegenerator.controllers.requests.ConfigurationsRequest

interface ConfigurationComponent {
    fun generateScript(configurations: ConfigurationsRequest): String
}
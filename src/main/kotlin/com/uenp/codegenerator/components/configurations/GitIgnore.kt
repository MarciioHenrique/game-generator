package com.uenp.codegenerator.components.configurations

class GitIgnore {
    fun generateScript(): String {
        return """
            .godot/
            .import/
            export.cfg
            export_presets.cfg
        """.trimIndent()
    }
}
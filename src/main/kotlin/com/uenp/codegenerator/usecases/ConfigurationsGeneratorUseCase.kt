package com.uenp.codegenerator.usecases

import com.uenp.codegenerator.components.configurations.ProjectConfiguration
import com.uenp.codegenerator.controllers.requests.ConfigurationsRequest
import com.uenp.codegenerator.utils.BASE_PATH
import com.uenp.codegenerator.utils.copyFile
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@Service
class ConfigurationsGeneratorUseCase {
    fun perform(configurations: ConfigurationsRequest, baseDir: File) {
        println("Generating configurations...")
        createProject(configurations, baseDir)
        copyFile(File("$BASE_PATH/assets/configurations/icon.png"), File(baseDir, "icon.png"))
    }

    private fun createProject(configurations: ConfigurationsRequest, baseDir: File) {
        val content = ProjectConfiguration().generateScript(configurations)
        val name = "project.godot"
        val path = Paths.get(baseDir.toString(), name)
        Files.write(path, content.toByteArray())
    }
}
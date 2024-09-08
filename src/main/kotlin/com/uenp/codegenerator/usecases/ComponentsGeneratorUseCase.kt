package com.uenp.codegenerator.usecases

import com.uenp.codegenerator.components.visuals.MenuComponent
import com.uenp.codegenerator.controllers.requests.ComponentsRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@Service
class ComponentsGeneratorUseCase {
    private val log = LoggerFactory.getLogger(javaClass)

    fun perform(components: ComponentsRequest, baseDir: File) {
        val scriptsDir = File(baseDir, "scripts")
        if (!scriptsDir.exists()) {
            scriptsDir.mkdirs()
        }

        val scenesDir = File(baseDir, "scenes")
        if (!scenesDir.exists()) {
            scenesDir.mkdirs()
        }

        if (components.menu == true) {
            log.info("Generating components for menu")
            val scriptContent = MenuComponent().generateScript()
            val scriptName = "menu.gd"
            val scriptPath = Paths.get(scriptsDir.toString(), scriptName)
            Files.write(scriptPath, scriptContent.toByteArray())

            val sceneContent = MenuComponent().generateScene()
            val sceneName = "menu.tscn"
            val scenePath = Paths.get(scenesDir.toString(), sceneName)
            Files.write(scenePath, sceneContent.toByteArray())
        }

    }
}
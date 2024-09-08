package com.uenp.codegenerator.usecases

import com.uenp.codegenerator.components.visuals.MenuComponent
import com.uenp.codegenerator.components.visuals.ScoreAndTimeComponent
import com.uenp.codegenerator.controllers.requests.ComponentsRequest
import com.uenp.codegenerator.domain.Directories
import com.uenp.codegenerator.utils.BASE_PATH
import com.uenp.codegenerator.utils.copyDirectory
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@Service
class ComponentsGeneratorUseCase {
    private val log = LoggerFactory.getLogger(javaClass)

    fun perform(components: ComponentsRequest, baseDir: File) {
        val scriptsDir = File(baseDir, Directories.SCRIPTS.folder)
        if (!scriptsDir.exists()) {
            scriptsDir.mkdirs()
        }

        val scenesDir = File(baseDir, Directories.SCENES.folder)
        if (!scenesDir.exists()) {
            scenesDir.mkdirs()
        }

        if (components.menu) {
            log.info("Generating components for menu")
            val scriptContent = MenuComponent().generateScript()
            val scriptName = "menu.gd"
            val scriptPath = Paths.get(scriptsDir.toString(), scriptName)
            Files.write(scriptPath, scriptContent.toByteArray())

            val sceneContent = MenuComponent().generateScene()
            val sceneName = "menu.tscn"
            val scenePath = Paths.get(scenesDir.toString(), sceneName)
            Files.write(scenePath, sceneContent.toByteArray())

            val menuDir = File(baseDir, Directories.MENU.folder)
            copyDirectory(Paths.get("$BASE_PATH/assets/components/menu"), Paths.get(menuDir.toString()))
        }

        if (components.scoreAndTime) {
            log.info("Generating components for score and time")
            val scriptContent = ScoreAndTimeComponent().generateScript()
            val scriptName = "scoreAndTime.gd"
            val scriptPath = Paths.get(scriptsDir.toString(), scriptName)
            Files.write(scriptPath, scriptContent.toByteArray())

            val sceneContent = ScoreAndTimeComponent().generateScene()
            val sceneName = "scoreAndTime.tscn"
            val scenePath = Paths.get(scenesDir.toString(), sceneName)
            Files.write(scenePath, sceneContent.toByteArray())
        }

    }
}
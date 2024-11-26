package com.uenp.codegenerator.application.usecases

import com.uenp.codegenerator.application.components.visuals.EndScreenComponent
import com.uenp.codegenerator.application.components.visuals.IntroComponent
import com.uenp.codegenerator.application.components.visuals.MenuComponent
import com.uenp.codegenerator.application.components.visuals.ScoreAndTimeComponent
import com.uenp.codegenerator.application.components.visuals.StartScreenComponent
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
            copyDirectory(Paths.get("$BASE_PATH/application/assets/components/menu"), Paths.get(menuDir.toString()))
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

        if (components.intro) {
            log.info("Generating components for intro")
            val introDir = File(baseDir, Directories.VIDEOS.folder)
            copyDirectory(Paths.get("$BASE_PATH/application/assets/components/intro"), Paths.get(introDir.toString()))

            val scriptContent = IntroComponent().generateScript()
            val scriptName = "intro.gd"
            val scriptPath = Paths.get(scriptsDir.toString(), scriptName)
            Files.write(scriptPath, scriptContent.toByteArray())

            val sceneContent = IntroComponent().generateScene()
            val sceneName = "intro.tscn"
            val scenePath = Paths.get(scenesDir.toString(), sceneName)
            Files.write(scenePath, sceneContent.toByteArray())
        }

        if (components.startScreen) {
            log.info("Generating components for start screen")
            val startScreenDir = File(baseDir, Directories.START_SCREEN.folder)
            copyDirectory(
                Paths.get("$BASE_PATH/application/assets/components/startScreen"),
                Paths.get(startScreenDir.toString())
            )

            val scriptContent = StartScreenComponent().generateScript()
            val scriptName = "startScreen.gd"
            val scriptPath = Paths.get(scriptsDir.toString(), scriptName)
            Files.write(scriptPath, scriptContent.toByteArray())

            val sceneContent = StartScreenComponent().generateScene()
            val sceneName = "startScreen.tscn"
            val scenePath = Paths.get(scenesDir.toString(), sceneName)
            Files.write(scenePath, sceneContent.toByteArray())
        }

        if (components.endScreen) {
            log.info("Generating components for end screen")
            val scriptContent = EndScreenComponent().generateScript()
            val scriptName = "endScreen.gd"
            val scriptPath = Paths.get(scriptsDir.toString(), scriptName)
            Files.write(scriptPath, scriptContent.toByteArray())

            val sceneContent = EndScreenComponent().generateScene()
            val sceneName = "endScreen.tscn"
            val scenePath = Paths.get(scenesDir.toString(), sceneName)
            Files.write(scenePath, sceneContent.toByteArray())
        }

    }
}
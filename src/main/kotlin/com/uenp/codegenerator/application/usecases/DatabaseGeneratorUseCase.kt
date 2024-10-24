package com.uenp.codegenerator.application.usecases

import com.uenp.codegenerator.controllers.requests.SelectedComponentsRequest
import com.uenp.codegenerator.domain.Directories
import com.uenp.codegenerator.domain.Stages
import com.uenp.codegenerator.domain.Vowels
import com.uenp.codegenerator.utils.BASE_PATH
import com.uenp.codegenerator.utils.copyDirectory
import com.uenp.codegenerator.utils.normalize
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Paths

@Service
class DatabaseGeneratorUseCase {
    fun perform(selectedComponentsRequest: SelectedComponentsRequest, baseDir: File) {
        val selectedScope = selectedComponentsRequest.scope
        if (selectedScope != null) {
            selectedScope.vowels?.map { vowel ->
                selectedScope.stages?.map { stage -> generateDatabase(vowel, stage, baseDir) }
            }

        }
    }

    private fun generateDatabase(vowel: Vowels, stage: Stages, baseDir: File) {
        val pathString =
            "$BASE_PATH/application/components/database/${normalize(stage.name)}/${normalize(vowel.name)}"
        val imagesDir = File(baseDir, Directories.IMAGES.folder)
        val soundsDir = File(baseDir, Directories.SOUNDS.folder)
        copyDirectory(Paths.get("$pathString/assets/images"), imagesDir.toPath())
        copyDirectory(Paths.get("$pathString/assets/sounds"), soundsDir.toPath())

        val wordsContent = File("$pathString/content.json").readText()
        val wordsDir = File(baseDir, Directories.IMPORT.folder)
        val wordsPath = Paths.get(wordsDir.toString(), "words.json")
        wordsPath.toFile().writeText(wordsContent)
    }
}
package com.uenp.codegenerator.usecases

import com.uenp.codegenerator.controllers.requests.SelectedComponentsRequest
import com.uenp.codegenerator.utils.normalize
import org.springframework.stereotype.Service
import java.io.File

@Service
class CodeGeneratorUseCase(
        private val configurationsGeneratorUseCase: ConfigurationsGeneratorUseCase,
        private val componentsGeneratorUseCase: ComponentsGeneratorUseCase
) {
    fun perform(selectedComponentsRequest: SelectedComponentsRequest): String {
        val outputDir = File(normalize(selectedComponentsRequest.configurations.projectName))
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }

        configurationsGeneratorUseCase.perform(selectedComponentsRequest.configurations, outputDir)
        componentsGeneratorUseCase.perform(selectedComponentsRequest.components!!, outputDir)

        return "Code generated"
    }
}
package com.uenp.codegenerator.controllers

import com.uenp.codegenerator.controllers.requests.SelectedComponentsRequest
import com.uenp.codegenerator.usecases.CodeGeneratorUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/code-generator")
class CodeGeneratorController(
        private val codeGeneratorUseCase: CodeGeneratorUseCase
) {
     @PostMapping
     fun generateCode(@RequestBody selectedComponentsRequest: SelectedComponentsRequest): ResponseEntity<String> {
         return ResponseEntity.ok(codeGeneratorUseCase.perform(selectedComponentsRequest))
     }
}
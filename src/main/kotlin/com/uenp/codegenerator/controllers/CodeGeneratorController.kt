package com.uenp.codegenerator.controllers

import com.uenp.codegenerator.application.usecases.CodeGeneratorUseCase
import com.uenp.codegenerator.controllers.requests.SelectedComponentsRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("/v1/code-generator")
class CodeGeneratorController(
    private val codeGeneratorUseCase: CodeGeneratorUseCase
) {
    @PostMapping
    fun generateCode(@RequestBody selectedComponentsRequest: SelectedComponentsRequest): ResponseEntity<ByteArray> {
        println("opa")
        val outputDir = codeGeneratorUseCase.perform(selectedComponentsRequest)
        val folderToZip = File(outputDir) // Pasta gerada no backend
        val zipFile = File(outputDir) // Caminho para o arquivo ZIP de saída

        // Cria o arquivo ZIP
        val path = zipFolder(folderToZip, zipFile)

        // Lê o arquivo ZIP em um array de bytes
        try {
            val fileContent = path.readBytes()
            // Processar o conteúdo do arquivo
            // Retorna o arquivo como resposta HTTP com o cabeçalho de download
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=${zipFile.name}")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileContent)
        } catch (e: IOException) {
            println("Erro ao ler o arquivo: ${e.message}")
            e.printStackTrace() // Isso ajudará a depurar o erro
        }
        return ResponseEntity.notFound().build()

    }

    fun zipFolder(folder: File, zipFile: File): File {
        try {
            // Usando o diretório temporário do sistema para salvar o arquivo ZIP
            val tempDir = Files.createTempDirectory("game-request-temp")
            val tempZipFile = tempDir.resolve("game-files.zip").toFile()

            ZipOutputStream(tempZipFile.outputStream()).use { zos ->
                folder.walk().forEach { file ->
                    if (file.isFile) {
                        val zipEntry = ZipEntry(file.relativeTo(folder).path)
                        zos.putNextEntry(zipEntry)
                        file.inputStream().copyTo(zos)
                        zos.closeEntry()
                    }
                }
            }

            // Agora você pode mover ou enviar o arquivo tempZipFile
            println("Arquivo ZIP gerado com sucesso em: ${tempZipFile.absolutePath}")
            return tempZipFile

        } catch (e: IOException) {
            e.printStackTrace()
            println("Erro ao criar o arquivo ZIP")
        }
        return File.createTempFile("error", ".zip")
    }
}
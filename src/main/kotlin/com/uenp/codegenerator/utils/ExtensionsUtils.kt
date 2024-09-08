package com.uenp.codegenerator.utils

import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

private val log = LoggerFactory.getLogger("ExtensionsUtils")

fun normalize(value: String): String {
    return value.lowercase().replace(" ", "-")
}

fun copyFile(source: File, target: File) {
    log.info("Copying file from ${source.absolutePath} to ${target.absolutePath}")
    Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING)
}

const val BASE_PATH = "src/main/kotlin/com/uenp/codegenerator"
package com.uenp.codegenerator.utils

import org.slf4j.LoggerFactory
import java.io.File
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.BasicFileAttributes

private val log = LoggerFactory.getLogger("ExtensionsUtils")

fun normalize(value: String): String {
    return value.lowercase().replace(" ", "-")
}

fun copyFile(source: File, target: File) {
    log.info("Copying file from ${source.absolutePath} to ${target.absolutePath}")
    Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING)
}

@Throws(IOException::class)
fun copyDirectory(sourceDir: Path, targetDir: Path) {
    log.info("Copying directory from ${sourceDir.toAbsolutePath()} to ${targetDir.toAbsolutePath()}")
    Files.walkFileTree(sourceDir, object : SimpleFileVisitor<Path>() {
        @Throws(IOException::class)
        override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
            val targetPath = targetDir.resolve(sourceDir.relativize(dir))
            try {
                Files.createDirectories(targetPath)
            } catch (e: FileAlreadyExistsException) {
                if (!Files.isDirectory(targetPath)) throw e
            }
            return FileVisitResult.CONTINUE
        }

        @Throws(IOException::class)
        override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
            Files.copy(file, targetDir.resolve(sourceDir.relativize(file)), StandardCopyOption.REPLACE_EXISTING)
            return FileVisitResult.CONTINUE
        }
    })
}

const val BASE_PATH = "src/main/kotlin/com/uenp/codegenerator"
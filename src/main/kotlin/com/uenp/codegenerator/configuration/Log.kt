package com.uenp.codegenerator.configuration

import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Log {
    companion object {
        inline val <reified T> T.logger: Logger
            get() = LoggerFactory.getLogger(T::class.java)

        inline fun <reified T> T.logInfo(context: String, message: String, reference: String?) =
                logger.info("[$context][${T::class.java.simpleName}] $message for $reference")

    }
}

package dev.be.logbackkotlin.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryController1 {

    private val logger = KotlinLogging.logger("SQL_LOG1")

    @GetMapping("/query1")
    fun query1(): String {
        logger.trace { "log --> TRACE" }
        logger.debug { "log --> DEBUG" }
        logger.info { "log --> INFO" }
        logger.warn { "log --> WARN" }
        logger.error { "log --> ERROR" }
        return "query1"
    }

}
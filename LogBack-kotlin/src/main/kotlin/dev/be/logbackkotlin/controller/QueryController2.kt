package dev.be.logbackkotlin.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryController2 {

    private val logger = KotlinLogging.logger("SQL_LOG2")

    @GetMapping("/query2")
    fun query2(): String {
        logger.trace { "log --> TRACE" }
        logger.debug { "log --> DEBUG" }
        logger.info { "log --> INFO" }
        logger.warn { "log --> WARN" }
        logger.error { "log --> ERROR" }
        return "query2"
    }

}
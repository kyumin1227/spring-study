package dev.be.logbackkotlin.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController {
    
    private val logger = KotlinLogging.logger {}

    @GetMapping("/demo")
    fun demo(): String {
        logger.trace { "log --> TRACE" }
        logger.debug { "log --> DEBUG" }
        logger.info { "log --> INFO" }
        logger.warn { "log --> WARN" }
        logger.error { "log --> ERROR" }

        return "demo"
    }

}
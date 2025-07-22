package dev.be.logbackkotlin.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.slf4j.MDC
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MdcController {

    private val logger = KotlinLogging.logger {}

    @GetMapping("/mdc")
    fun mdc(): String {
        MDC.put("job", "dev")
        logger.trace { "log --> TRACE" }
        logger.debug { "log --> DEBUG" }
        logger.info { "log --> INFO" }
        logger.warn { "log --> WARN" }
        logger.error { "log --> ERROR" }
        MDC.clear()
        return "mdc"
    }
}
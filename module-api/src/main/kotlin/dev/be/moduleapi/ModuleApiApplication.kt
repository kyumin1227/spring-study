package dev.be.moduleapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication (
    scanBasePackages = [ "dev.be.moduleapi", "dev.be.modulecommon" ]
)
class ModuleApiApplication

fun main(args: Array<String>) {
    runApplication<ModuleApiApplication>(*args)
}

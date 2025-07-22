package dev.be.logbackkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LogBackKotlinApplication

fun main(args: Array<String>) {
    runApplication<LogBackKotlinApplication>(*args)
}

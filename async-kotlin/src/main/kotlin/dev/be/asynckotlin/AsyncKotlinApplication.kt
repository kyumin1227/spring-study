package dev.be.asynckotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AsyncKotlinApplication

fun main(args: Array<String>) {
    runApplication<AsyncKotlinApplication>(*args)
}

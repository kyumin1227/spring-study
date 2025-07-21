package dev.be.moduleapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication (
    scanBasePackages = [ "dev.be.moduleapi", "dev.be.modulecommon" ]
)
@EntityScan("dev.be.modulecommon.domain")
@EnableJpaRepositories(
    basePackages = ["dev.be.modulecommon.repositories"]
)
class ModuleApiApplication

fun main(args: Array<String>) {
    runApplication<ModuleApiApplication>(*args)
}

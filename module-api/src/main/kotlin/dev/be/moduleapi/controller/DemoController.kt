package dev.be.moduleapi.controller

import dev.be.moduleapi.service.DemoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController(
    private val demoService: DemoService
) {

    @GetMapping("/save")
    fun save(): String {
        return demoService.save()
    }

    @GetMapping("/find")
    fun find(): String {
        return demoService.find()
    }

    @GetMapping("/exception")
    fun exception(): String {
        return demoService.exception()
    }

}
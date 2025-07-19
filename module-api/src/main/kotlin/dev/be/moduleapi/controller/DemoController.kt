package dev.be.moduleapi.controller

import dev.be.moduleapi.service.DemoService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
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

}
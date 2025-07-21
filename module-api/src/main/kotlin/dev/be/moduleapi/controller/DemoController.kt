package dev.be.moduleapi.controller

import dev.be.moduleapi.service.DemoService
import dev.be.modulecommon.domain.Member
import dev.be.modulecommon.repositories.MemberRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController(
    private val demoService: DemoService,
    private val memberRepository: MemberRepository
) {

    @GetMapping("/save")
    fun save(): String {
        memberRepository.save(Member(name = Thread.currentThread().name))
        return demoService.save()
    }

    @GetMapping("/find")
    fun find(): String {
        val size = memberRepository.findAll().size
        println(size)
        return demoService.find()
    }

    @GetMapping("/exception")
    fun exception(): String {
        return demoService.exception()
    }

}
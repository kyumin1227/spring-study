package dev.be.moduleapi.service

import dev.be.moduleapi.exception.CustomException
import dev.be.modulecommon.enums.CodeEnum
import dev.be.modulecommon.service.CommonDemoService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DemoService(
    private val commonDemoService: CommonDemoService,
    @Value("\${profile-name}") private val name: String
) {

    fun save(): String {
        println(CodeEnum.SUCCESS.code)
        println(commonDemoService.commonService())
        return "save"
    }

    fun find(): String {
        println("profile-name: $name")
        return "find"
    }

    fun exception(): String {
        if (true) {
            throw CustomException(CodeEnum.UNKNOWN_ERROR)
        }
        return "exception"
    }

}
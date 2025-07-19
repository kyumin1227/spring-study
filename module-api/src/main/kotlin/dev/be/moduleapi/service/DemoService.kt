package dev.be.moduleapi.service

import dev.be.modulecommon.enums.CodeEnum
import dev.be.modulecommon.service.CommonDemoService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class DemoService(
    private val commonDemoService: CommonDemoService
) {

    fun save(): String {
        println(CodeEnum.SUCCESS.code)
        println(commonDemoService.commonService())
        return "save"
    }

    fun find(): String {
        return "find"
    }

}
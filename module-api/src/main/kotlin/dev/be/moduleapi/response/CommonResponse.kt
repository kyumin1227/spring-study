package dev.be.moduleapi.response

import com.fasterxml.jackson.annotation.JsonInclude
import dev.be.modulecommon.enums.CodeEnum

@JsonInclude(JsonInclude.Include.NON_NULL)
class CommonResponse<T>(
    val returnCode: String,
    val returnMessage: String,
    val info: T?
) {
    companion object {

        fun <T> success(info: T?): CommonResponse<T?> {
            return CommonResponse(CodeEnum.SUCCESS.code, CodeEnum.SUCCESS.message, info)
        }

        fun <T> of(codeEnum: CodeEnum, info: T?): CommonResponse<T?> {
            return CommonResponse(codeEnum.code, codeEnum.message, info)
        }

        fun <T> of(code: String, message: String): CommonResponse<T?> {
            return CommonResponse(code, message, null)
        }
    }
}
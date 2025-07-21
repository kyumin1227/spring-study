package dev.be.moduleapi.exceptionhandler

import dev.be.moduleapi.exception.CustomException
import dev.be.moduleapi.response.CommonResponse
import dev.be.modulecommon.enums.CodeEnum
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CustomException::class)
    fun handlerCustomException(
        exception: CustomException
    ): CommonResponse<Unit?> {
        return CommonResponse.of(
            exception.returnCode,
            exception.returnMessage
        )
    }

    @ExceptionHandler(Exception::class)
    fun handlerException(
        exception: Exception
    ): CommonResponse<Unit?> {
        return CommonResponse.of(
            CodeEnum.UNKNOWN_ERROR,
            null
        )
    }

}
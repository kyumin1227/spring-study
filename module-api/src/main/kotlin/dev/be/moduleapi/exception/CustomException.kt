package dev.be.moduleapi.exception

import dev.be.modulecommon.enums.CodeEnum

class CustomException(
    val returnCode: String,
    val returnMessage: String
) : RuntimeException() {

    constructor(codeEnum: CodeEnum): this(
        codeEnum.code,
        codeEnum.message
    )

}
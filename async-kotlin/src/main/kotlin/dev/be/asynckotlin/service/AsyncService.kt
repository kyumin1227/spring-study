package dev.be.asynckotlin.service

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class AsyncService(
    val emailService: EmailService
) {

    fun asyncCall_1() {
        println("[asyncCall_1] :: ${Thread.currentThread().name}")
        emailService.sendMail()
        emailService.sendMailWithCustomThreadPool()
//        [asyncCall_1] :: http-nio-8080-exec-1
//        [sendMail] :: defaultTaskExecutor-1
//        [sendMailWithCustomThreadPool] :: messagingTaskExecutor-1
    }

    fun asyncCall_2() {
        println("[asyncCall_2] :: ${Thread.currentThread().name}")
        val emailService = EmailService()
        emailService.sendMail()
        emailService.sendMailWithCustomThreadPool()
//        [asyncCall_2] :: http-nio-8080-exec-2
//        [sendMail] :: http-nio-8080-exec-2
//        [sendMailWithCustomThreadPool] :: http-nio-8080-exec-2
    }

    fun asyncCall_3() {
        println("[asyncCall_3] :: ${Thread.currentThread().name}")
        sendMail()
//        [asyncCall_3] :: http-nio-8080-exec-5
//        [sendMail] :: http-nio-8080-exec-5
    }

    @Async
    fun sendMail() {
        println("[sendMail] :: ${Thread.currentThread().name}")
    }

}
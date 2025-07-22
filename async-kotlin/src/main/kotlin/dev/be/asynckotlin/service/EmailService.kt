package dev.be.asynckotlin.service

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class EmailService {

    @Async("defaultTaskExecutor")
    fun sendMail() {
        println("[sendMail] :: ${Thread.currentThread().name}")
    }

    @Async("messagingTaskExecutor")
    fun sendMailWithCustomThreadPool() {
        println("[sendMailWithCustomThreadPool] :: ${Thread.currentThread().name}")
    }
}
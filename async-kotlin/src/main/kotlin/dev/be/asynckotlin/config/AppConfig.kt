package dev.be.asynckotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
class AppConfig {

    @Bean(name = ["defaultTaskExecutor"], destroyMethod = "shutdown")
    fun defaultTaskExecutor(): ThreadPoolTaskExecutor {
        val executor = ThreadPoolTaskExecutor().apply {
            corePoolSize = 200
            maxPoolSize = 300
        }
        return executor
    }

    @Bean(name = ["messagingTaskExecutor"], destroyMethod = "shutdown")
    fun messagingTaskExecutor(): ThreadPoolTaskExecutor {
        val executor = ThreadPoolTaskExecutor().apply {
            corePoolSize = 200
            maxPoolSize = 300
        }
        return executor
    }

}
package dev.be.async.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {
//    비동기 처리를 위해서는 스프링 컨테이너에 등록된 Bean을 사용

    private final EmailService emailService;

    public void asyncCall_1() {
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
//        [asyncCall_1] :: http-nio-8080-exec-1
//        [sendMail] :: defaultTaskExecutor-1
//        [sendMailWithCustomThreadPool] :: messagingTaskExecutor-1
    }

    public void asyncCall_2() {
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
        EmailService emailService = new EmailService();
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
//        [asyncCall_2] :: http-nio-8080-exec-2
//        [sendMail] :: http-nio-8080-exec-2
//        [sendMailWithCustomThreadPool] :: http-nio-8080-exec-2
    }

    public void asyncCall_3() {
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
        sendMail();
//        [asyncCall_3] :: http-nio-8080-exec-4
//        [sendMail] :: http-nio-8080-exec-4

    }

    @Async
    public void sendMail() {
        System.out.println("[sendMail] :: " + Thread.currentThread().getName());
    }
}

package com.colak.springtutorial.service.rabbit;

import com.colak.springtutorial.service.email.EmailDetails;
import com.colak.springtutorial.service.email.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitListenerService {

    @Value("${spring.mail.username}")
    private String emailSender;

    private final EmailSenderService emailSenderService;

    @RabbitListener(queues = "email_queue")
    public void handleMessage(EmailDetails emailDetails) {
        log.info("Received : {}", emailDetails);
        emailSenderService.sendEmail(emailDetails);
    }
}

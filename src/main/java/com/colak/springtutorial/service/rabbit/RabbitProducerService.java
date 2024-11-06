package com.colak.springtutorial.service.rabbit;

import com.colak.springtutorial.dto.UserDto;
import com.colak.springtutorial.service.email.EmailDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitProducerService {

    @Value("${rabbitmq.exchange.email.name}")
    private String emailExchange;

    @Value("${rabbitmq.binding.email.name}")
    private String emailRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    @Async
    public void sendEmail(UserDto userDto) {
        EmailDetails registrationSuccess = EmailDetails.builder()
                .to(userDto.getEmail())
                .subject("REGISTRATION SUCCESS")
                .text("Registration Successful with mail id: " + userDto.getEmail())
                .build();

        rabbitTemplate.convertAndSend(emailExchange,
                emailRoutingKey,
                registrationSuccess);
    }
}

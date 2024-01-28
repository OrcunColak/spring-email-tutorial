package com.colak.springemailtutorial.service.rabbit;

import com.colak.springemailtutorial.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class RabbitProducerServiceTest {

    @Autowired
    private RabbitProducerService rabbitProducerService;

    @Test
    void testSendEmail() {
        UserDto userDto = new UserDto("firstName", "lastName", "orcuncolak@yahoo.com", "password");
        assertDoesNotThrow(() -> rabbitProducerService.sendEmail(userDto));
    }
}

package com.colak.springemailtutorial.service.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EmailSenderServiceTest {

    @Autowired
    private EmailSenderService emailSenderService;

    @Test
    void testSendEmail() {
        EmailDetails emailDetails = new EmailDetails("orcuncolak@yahoo.com", "messageBody", "subject");
        assertDoesNotThrow(() -> emailSenderService.sendEmail(emailDetails));
    }
}

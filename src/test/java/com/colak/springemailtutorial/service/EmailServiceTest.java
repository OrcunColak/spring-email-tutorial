package com.colak.springemailtutorial.service;

import com.colak.springemailtutorial.service.email.EmailDetails;
import com.colak.springemailtutorial.service.email.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail() {
        EmailDetails emailDetails = new EmailDetails("orcuncolak@yahoo.com", "messageBody", "subject");
        assertDoesNotThrow(() -> emailService.sendEmail(emailDetails));
    }
}

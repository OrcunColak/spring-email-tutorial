package com.colak.springemailtutorial.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    private EmailService emailService;


    @Test
    void testSendEmail() {
        EmailDetails emailDetails = new EmailDetails("orcuncolak@yahoo.com", "messageBody", "subject");
        emailService.sendEmail(emailDetails);
    }
}

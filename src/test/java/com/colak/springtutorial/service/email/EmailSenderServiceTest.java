package com.colak.springtutorial.service.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EmailSenderServiceTest {

    @Autowired
    private EmailSenderService emailSenderService;

    @Test
    void testSendHtmlEmail() {
        String body = """
                <html>
                  <body>
                    <p>Hello, world</p>
                  </body>
                </html>""";

        EmailDetails emailDetails = EmailDetails.builder()
                .to("foo@yahoo.com")
                .subject("subject")
                .body(body)
                .simpleMessage(false)
                .template(false)
                .build();
        assertDoesNotThrow(() -> emailSenderService.sendEmail(emailDetails));
    }

    @Test
    void testSendTemplateEmail() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", "Orçun");

        EmailDetails emailDetails = EmailDetails.builder()
                .to("foo@yahoo.com")
                .subject("subject")
                .simpleMessage(false)
                .template(true)
                .templateName("emailTemplate")
                .thymeleafTemplate(true)
                .variables(variables)
                .build();
        assertDoesNotThrow(() -> emailSenderService.sendEmail(emailDetails));
    }
}

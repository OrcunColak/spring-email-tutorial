package com.colak.springtutorial.service.email;

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
        String html = """
                  <html>
                    <body>
                      <p>Hello, world</p>
                    </body>
                  </html>
                """;
        EmailDetails emailDetails = new EmailDetails("orcuncolak@yahoo.com", html, "subject", false, true);
        assertDoesNotThrow(() -> emailSenderService.sendEmail(emailDetails));
    }
}

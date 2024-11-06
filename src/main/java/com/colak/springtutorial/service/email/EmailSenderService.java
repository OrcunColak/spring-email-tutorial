package com.colak.springtutorial.service.email;

import com.colak.springtutorial.service.email.mailcreators.MimeMailService;
import com.colak.springtutorial.service.email.mailcreators.SimpleEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;
    private final SimpleEmailService simpleEmailService;
    private final MimeMailService mimeMailService;

    @Async
    public void sendEmail(EmailDetails emailDetails) {
        try {
            if (emailDetails.isSimpleMessage()) {
                SimpleMailMessage mailMsg = simpleEmailService.createSimpleMailMessage(emailDetails);
                javaMailSender.send(mailMsg);
                log.info("Simple Mail sent successfully");
            } else {
                MimeMessage mimeMessage = mimeMailService.createMimeMailMessage(emailDetails);
                javaMailSender.send(mimeMessage);
                log.info("Mime Mail sent successfully");
            }
        } catch (MailException | MessagingException exception) {
            log.error("Failure occurred while sending email", exception);
        }
    }
}

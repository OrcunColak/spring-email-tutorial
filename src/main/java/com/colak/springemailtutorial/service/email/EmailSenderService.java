package com.colak.springemailtutorial.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    @Async
    public void sendEmail(EmailDetails emailDetails) {
        try {
            if (emailDetails.isSimpleMessage()) {
                SimpleMailMessage mailMsg = createSimpleMailMessage(emailDetails);
                javaMailSender.send(mailMsg);
                log.info("Mail sent successfully");
            } else {
                MimeMessage mimeMessage = createMimeMailMessage(emailDetails);
                javaMailSender.send(mimeMessage);
                log.info("Mail sent successfully");
            }
        } catch (MailException | MessagingException exception) {
            log.error("Failure occurred while sending email", exception);
        }
    }

    private SimpleMailMessage createSimpleMailMessage(EmailDetails emailDetails) {

        // SimpleMailMessage is spring class
        SimpleMailMessage mailMsg = new SimpleMailMessage();
        mailMsg.setFrom(emailSender);
        mailMsg.setTo(emailDetails.getTo());
        mailMsg.setSubject(emailDetails.getSubject());
        mailMsg.setText(emailDetails.getText());
        return mailMsg;
    }

    private MimeMessage createMimeMailMessage(EmailDetails emailDetails) throws MessagingException {
        // MimeMessage is jakarta class
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        // MimeMessageHelper is spring class
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        mimeMessageHelper.setTo(emailDetails.getTo());
        mimeMessageHelper.setSubject(emailDetails.getSubject());
        mimeMessageHelper.setFrom(this.emailSender);
        mimeMessageHelper.setText(emailDetails.getText(), emailDetails.isHtml());
        return mimeMessage;
    }
}

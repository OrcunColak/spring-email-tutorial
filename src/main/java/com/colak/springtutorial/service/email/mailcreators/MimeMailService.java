package com.colak.springtutorial.service.email.mailcreators;

import com.colak.springtutorial.service.email.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
@RequiredArgsConstructor
public class MimeMailService {

    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromUserName;

    public MimeMessage createMimeMailMessage(EmailDetails emailDetails) throws MessagingException {
        // MimeMessage is jakarta class
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        // MimeMessageHelper is spring class
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        mimeMessageHelper.setTo(emailDetails.getTo());
        mimeMessageHelper.setSubject(emailDetails.getSubject());
        mimeMessageHelper.setFrom(fromUserName);

        if (emailDetails.isHtml()) {
            mimeMessageHelper.setText(emailDetails.getText(), true);
        } else {
            mimeMessageHelper.setText(emailDetails.getText(), false);
        }
        return mimeMessage;
    }
}

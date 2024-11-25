package com.colak.springtutorial.service.email.mailcreators;

import com.colak.springtutorial.service.email.EmailDetails;
import com.colak.springtutorial.service.email.mailcreators.templatefacade.TemplateFacade;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MimeMailService {

    private final JavaMailSender javaMailSender;

    private final TemplateFacade templateFacade;


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

        if (emailDetails.isTemplate()) {
            String body;
            String templateName = emailDetails.getTemplateName();
            Map<String, Object> variables = emailDetails.getVariables();

            if (emailDetails.isThymeleafTemplate()) {
                body = templateFacade.thymeleafEmailContent(templateName, variables);
            } else {
                body = templateFacade.freeMarkerEmailContent(templateName, variables);
            }
            mimeMessageHelper.setText(body, true);

        } else {
            mimeMessageHelper.setText(emailDetails.getBody(), true);
        }
        return mimeMessage;
    }
}

package com.colak.springtutorial.service.email.mailcreators;

import com.colak.springtutorial.service.email.EmailDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    @Value("${spring.mail.username:my-user}")
    private String fromUserName;

    public SimpleMailMessage createSimpleMailMessage(EmailDetails emailDetails) {
        // SimpleMailMessage is spring class
        SimpleMailMessage mailMsg = new SimpleMailMessage();
        mailMsg.setFrom(fromUserName);
        mailMsg.setTo(emailDetails.getTo());
        mailMsg.setSubject(emailDetails.getSubject());
        mailMsg.setText(emailDetails.getText());
        return mailMsg;
    }
}

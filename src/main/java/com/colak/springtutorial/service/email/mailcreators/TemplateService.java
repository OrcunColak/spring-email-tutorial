package com.colak.springtutorial.service.email.mailcreators;

import lombok.RequiredArgsConstructor;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@RequiredArgsConstructor
public class TemplateService {

    private final TemplateEngine templateEngine;

    public String buildEmailContent(String htmlTemplate, Map<String, Object> variables) {
        Context context = new Context();
        context.setVariables(variables); // Set variables to be used in template

        // Context context = new Context();
        // context.setVariable("subject", subject);
        // context.setVariable("message", message);
        // String htmlBody = templateEngine.process("emailTemplate", context);
        return templateEngine.process(htmlTemplate, context);
    }
}

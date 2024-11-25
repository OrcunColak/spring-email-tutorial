package com.colak.springtutorial.service.email.mailcreators.thymeleaf;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ThymeleafTemplateService {

    private final TemplateEngine templateEngine;

    public String buildEmailContent(String htmlTemplateName, Map<String, Object> variables) {
        Context context = new Context();
        context.setVariables(variables); // Set variables to be used in template

        // Context context = new Context();
        // context.setVariable("subject", subject);
        // context.setVariable("message", message);
        // String htmlBody = templateEngine.process("emailTemplate", context);
        return templateEngine.process(htmlTemplateName, context);
    }
}

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

        return templateEngine.process(htmlTemplate, context);
    }
}

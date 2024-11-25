package com.colak.springtutorial.service.email.mailcreators.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FreeMarkerTemplateService {

    private final Configuration freeMarkerConfiguration;

    public String buildEmailContent(String htmlTemplate, Map<String, Object> variables) {
        try {
            Template template = freeMarkerConfiguration.getTemplate(htmlTemplate);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, variables);
        } catch (IOException| TemplateException exception) {
            return "Email could not be delivered!";
        }

    }
}

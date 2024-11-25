package com.colak.springtutorial.service.email.mailcreators.templatefacade;

import com.colak.springtutorial.service.email.mailcreators.freemarker.FreeMarkerTemplateService;
import com.colak.springtutorial.service.email.mailcreators.thymeleaf.ThymeleafTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TemplateFacade {

    private final ThymeleafTemplateService thymeleafTemplateService;

    private final FreeMarkerTemplateService freeMarkerTemplateService;

    public String thymeleafEmailContent(String htmlTemplateName, Map<String, Object> variables) {
        return thymeleafTemplateService.buildEmailContent(htmlTemplateName, variables);
    }

    public String freeMarkerEmailContent(String htmlTemplateName, Map<String, Object> variables) {
        return freeMarkerTemplateService.buildEmailContent(htmlTemplateName, variables);
    }
}

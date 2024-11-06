package com.colak.springtutorial.service.email.mailcreators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateServiceTest {

    private TemplateService templateService;

    @BeforeEach
    public void setUp() {
        // Use ClassLoaderTemplateResolver to avoid needing an ApplicationContext
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");  // The path within src/main/resources
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCacheable(false);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        templateService = new TemplateService(templateEngine);
    }

    @Test
    public void testBuildEmailContent() {
        // Arrange
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", "Orçun");

        // Act
        String result = templateService.buildEmailContent("emailTemplate", variables);

        // Assert
        String expectedOutput = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Email Template</title>
                </head>
                <body>
                <p>Hello, <strong>Orçun</strong>!</p>
                </body>
                </html>""";
        assertEquals(expectedOutput, result);
    }
}

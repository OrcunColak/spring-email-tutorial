package com.colak.springtutorial.service.email.mailcreators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateServiceStringTemplateTest {

    private TemplateService templateService;

    @BeforeEach
    public void setUp() {
        // Configure the TemplateEngine with a StringTemplateResolver
        TemplateEngine templateEngine = new TemplateEngine();
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCacheable(false);
        templateEngine.setTemplateResolver(templateResolver);

        templateService = new TemplateService(templateEngine);
    }

    @Test
    public void testBuildEmailContent() {
        // Arrange
        String htmlTemplate = """
                <html>
                    <body>
                        <p>Hello, <strong th:text="${name}"></strong>!</p>
                    </body>
                </html>""";

        Map<String, Object> variables = new HashMap<>();
        variables.put("name", "Orçun");

        // Act
        String result = templateService.buildEmailContent(htmlTemplate, variables);

        // Assert
        String expectedOutput = """
                <html>
                    <body>
                        <p>Hello, <strong>Orçun</strong>!</p>
                    </body>
                </html>""";
        assertEquals(expectedOutput, result);
    }
}

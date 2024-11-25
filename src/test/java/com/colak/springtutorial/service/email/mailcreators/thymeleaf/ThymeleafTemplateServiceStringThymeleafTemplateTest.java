package com.colak.springtutorial.service.email.mailcreators.thymeleaf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThymeleafTemplateServiceStringThymeleafTemplateTest {

    private ThymeleafTemplateService thymeleafTemplateService;

    @BeforeEach
    public void setUp() {
        // Configure the TemplateEngine with a StringTemplateResolver
        TemplateEngine templateEngine = new TemplateEngine();
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCacheable(false);
        templateEngine.setTemplateResolver(templateResolver);

        thymeleafTemplateService = new ThymeleafTemplateService(templateEngine);
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
        String result = thymeleafTemplateService.buildEmailContent(htmlTemplate, variables);

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

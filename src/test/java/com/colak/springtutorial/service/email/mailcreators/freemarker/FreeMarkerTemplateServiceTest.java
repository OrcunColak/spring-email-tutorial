package com.colak.springtutorial.service.email.mailcreators.freemarker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FreeMarkerTemplateServiceTest {

    @Autowired
    private FreeMarkerTemplateService templateService;

    @Test
    public void testBuildEmailContent() {
        Map<String, Object> variables = Map.of("firstName", "Orcun", "lastName", "Colak", "regLink", "mylink");
        String result = templateService.buildEmailContent("ConfirmEmail.ftl", variables);

        String expectedOutput = """
                <html>
                <head></head>
                <body>
                    <p>Hello Orcun Colak,</p>
                    <p>You have requested to change your password, You can change your password by clicking given link
                    <a href=mylink>click link</a>
                    The link will expire after 2 hours.</p>
                    <p>Thanks,</p>
                    <p>Admin group</p>
                    <p>Numpy Ninja</p>
                    <p></p>
                </body>
                </html>""";
        assertEquals(expectedOutput, result);


    }

}
package com.colak.springtutorial.service.email;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class EmailDetails {

    private String to;
    private String subject;
    private String body;
    // Simple or Mime message
    private boolean simpleMessage;

    // If mime message html or not
    private boolean template;

    private boolean thymeleafTemplate;

    private String templateName;

    private Map<String, Object> variables;
}
package com.colak.springemailtutorial.service.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDetails {

    private String to;
    private String text;
    private String subject;
    private boolean simpleMessage;
    private boolean html;
}
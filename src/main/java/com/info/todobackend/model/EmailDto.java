package com.info.todobackend.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmailDto {

    private String from;
    private String to;
    private String subject;
    private String message;
    private String templateName;
    private String templateLocation;
    private String emailedMessage;

}

package com.info.todobackend.model.helper;

import com.info.todobackend.model.EmailDto;
import com.info.todobackend.model.SystemUser;
import org.thymeleaf.context.Context;

public class EmailHelper {

    public static EmailDto prepareRegistrationMail(SystemUser user) {
        EmailDto email = new EmailDto();
        email.setTo(user.getEmail());
        email.setSubject("todo app - confirm registration");
        email.setFrom("todo@app");
        email.setTemplateName("registration");
        return email;
    }

    public static Context prepareRegistrationContext(SystemUser user) {
        Context context = new Context();
        context.setVariable("name", user.getFirstName().concat(" ".concat(user.getLastName())));
        context.setVariable("activationLink", "http://localhost:4200/activationm na/".concat(user.getActivationCode()));
        return context;
    }


}

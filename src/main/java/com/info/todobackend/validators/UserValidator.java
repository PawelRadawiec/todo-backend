package com.info.todobackend.validators;

import com.info.todobackend.model.SystemUser;
import com.info.todobackend.service.operations.UserOperations;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private UserOperations userOperations;

    public UserValidator(UserOperations userOperations) {
        this.userOperations = userOperations;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SystemUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}

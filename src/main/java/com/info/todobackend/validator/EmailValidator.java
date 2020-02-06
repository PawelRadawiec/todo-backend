package com.info.todobackend.validator;


import com.info.todobackend.model.SystemUser;
import com.info.todobackend.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private UserRepository dto;

    public EmailValidator(UserRepository dto) {
        this.dto = dto;
    }

    @Override
    public void initialize(ValidEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(email)) {
            return true;
        }
        SystemUser systemUser = dto.findByEmail(email);
        return systemUser == null;
    }
}

package com.info.todobackend.validator;

import com.info.todobackend.model.SystemUser;
import com.info.todobackend.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class LoginValidator implements ConstraintValidator<ValidLogin, String> {

    private UserRepository dto;

    public LoginValidator(UserRepository dto) {
        this.dto = dto;
    }

    @Override
    public void initialize(ValidLogin constraintAnnotation) {

    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(login)) {
            return true;
        }
        SystemUser systemUser = dto.findSystemUserByLogin(login);
        return systemUser == null;
    }
}

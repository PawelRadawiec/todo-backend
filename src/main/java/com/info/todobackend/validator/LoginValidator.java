package com.info.todobackend.validator;

import com.info.todobackend.model.SystemUser;
import com.info.todobackend.repository.UserRepository;
import com.info.todobackend.validator.annotations.ValidLogin;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class LoginValidator implements ConstraintValidator<ValidLogin, String> {

    private UserRepository dto;

    public LoginValidator(UserRepository dto) {
        this.dto = dto;
    }

    @Override
    public void initialize(ValidLogin constraintAnnotation) {

    }

    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(login)) {
            return true;
        }
        SystemUser systemUser = dto.findSystemUserByLogin(login);
        return systemUser == null;
    }
}

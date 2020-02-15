package com.info.todobackend.validator.annotations;

import com.info.todobackend.validator.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmailValidator.class})
@Documented
public @interface ValidEmail {

    String message() default "email must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

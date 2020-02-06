package com.info.todobackend.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {LoginValidator.class})
@Documented
public @interface ValidLogin {

    String message() default "login must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

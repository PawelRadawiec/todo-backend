package com.info.todobackend.validator;

import com.info.todobackend.model.todo.Todo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TodoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Todo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Todo todo = (Todo) o;
        if (StringUtils.isEmpty(todo.getTitle())) {
            errors.rejectValue("title", "must_be_set", "must be set");
        }
        if (StringUtils.isEmpty(todo.getDescription())) {
            errors.rejectValue("description", "must_be_set","must be set");
        }
        if (StringUtils.isEmpty(todo.getStatus())) {
            errors.rejectValue("status", "must_be_set","must be set");
        }
    }


}

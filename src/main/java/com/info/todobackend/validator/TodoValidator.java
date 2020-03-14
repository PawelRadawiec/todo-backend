package com.info.todobackend.validator;

import com.info.todobackend.helper.TodoHelper;
import com.info.todobackend.model.todo.Todo;
import com.info.todobackend.repository.entityManager.TodoEmRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TodoValidator implements Validator {

    private TodoEmRepository emRepository;

    public TodoValidator(TodoEmRepository emRepository) {
        this.emRepository = emRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Todo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Todo todo = (Todo) o;
        validateBasic(todo, errors);
        validateTitle(todo, errors);
    }

    private void validateBasic(Todo todo, Errors errors) {
        if (StringUtils.isEmpty(todo.getTitle())) {
            errors.rejectValue("title", "must_be_set", "must be set");
        }
        if (StringUtils.isEmpty(todo.getDescription())) {
            errors.rejectValue("description", "must_be_set", "must be set");
        }
    }

    private void validateTitle(Todo todo, Errors errors) {
        if (StringUtils.isEmpty(todo.getTitle())) {
            return;
        }
        if (containsWhitespace(todo.getTitle())) {
            errors.rejectValue("title", "wrong_value", "cannot contains whitespace");
        }
        validateTitleUniqueness(todo, errors);
    }

    private void validateTitleUniqueness(Todo todo, Errors errors) {
        if (StringUtils.isEmpty(todo.getTitle()) || containsWhitespace(todo.getTitle())) {
            return;
        }
        Optional<Todo> todoByTitle = emRepository.findByTitleAndProjectId(todo.getTitle(), todo.getProject().getId());
        if (todo.getId() != null && todoByTitle.isPresent() && (todo.getId().equals(todoByTitle.get().getId()))) {
            return;
        }
        if (todoByTitle.isPresent()) {
            errors.rejectValue("title", "must_be_unique", "must be unique");
        }
    }

    private Boolean containsWhitespace(String value) {
        Pattern pattern = Pattern.compile("\\s");
        return pattern.matcher(value).find();
    }


}

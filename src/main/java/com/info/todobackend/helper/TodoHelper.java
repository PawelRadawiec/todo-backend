package com.info.todobackend.helper;

import com.info.todobackend.model.todo.Todo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class TodoHelper {

    public void merge(Todo todo) {
        todo.setAuthor(currentUserName());
    }

    private String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}

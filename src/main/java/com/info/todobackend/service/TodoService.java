package com.info.todobackend.service;

import com.info.todobackend.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    Todo create(Todo todo);
    List<Todo> getAll();
    Optional<Todo> getById(Long id);
    Todo update(Todo todo);
}

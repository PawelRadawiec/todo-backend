package com.info.todobackend.service.operations;

import com.info.todobackend.model.todo.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoOperations {
    Todo create(Todo todo);
    List<Todo> getAll();
    Optional<Todo> getById(Long id);
    Todo update(Todo todo);
    void deleteById(Long id);
}

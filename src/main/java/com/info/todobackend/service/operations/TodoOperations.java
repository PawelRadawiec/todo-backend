package com.info.todobackend.service.operations;

import com.info.todobackend.model.todo.Todo;
import com.info.todobackend.model.todo.filter.TodoFilter;

import java.util.List;
import java.util.Optional;

public interface TodoOperations {
    Todo create(Todo todo);
    List<Todo> search(TodoFilter filter);
    Optional<Todo> getById(Long id);
    Todo update(Todo todo);
    void deleteById(Long id);
}

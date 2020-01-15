package com.info.todobackend.service;

import com.info.todobackend.model.todo.Todo;
import com.info.todobackend.repository.SubtaskRepository;
import com.info.todobackend.repository.TodoRepository;
import com.info.todobackend.service.operations.TodoOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService implements TodoOperations {

    private TodoRepository todoDao;

    public TodoService(TodoRepository todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    public Todo create(Todo todo) {
        return todoDao.save(todo);
    }

    @Override
    public List<Todo> getAll() {
        return todoDao.findAll();
    }

    @Override
    public Optional<Todo> getById(Long id) {
        return todoDao.findById(id);
    }

    @Override
    public Todo update(Todo todo) {
        return todoDao.save(todo);
    }

    @Override
    public void deleteById(Long id) {
        todoDao.deleteById(id);
    }
}

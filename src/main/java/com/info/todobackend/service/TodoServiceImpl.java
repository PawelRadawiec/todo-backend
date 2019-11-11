package com.info.todobackend.service;

import com.info.todobackend.model.Todo;
import com.info.todobackend.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoDao;

    public TodoServiceImpl(TodoRepository todoDao) {
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
}

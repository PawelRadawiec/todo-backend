package com.info.todobackend.service;

import com.info.todobackend.model.Todo;
import com.info.todobackend.repository.TodoRepository;
import org.springframework.stereotype.Service;

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
}

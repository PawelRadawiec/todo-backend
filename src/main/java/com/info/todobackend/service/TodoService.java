package com.info.todobackend.service;

import com.info.todobackend.helper.TodoHelper;
import com.info.todobackend.model.todo.Todo;
import com.info.todobackend.model.todo.filter.TodoFilter;
import com.info.todobackend.repository.TodoRepository;
import com.info.todobackend.service.operations.TodoOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService implements TodoOperations {

    private TodoRepository todoDao;
    private TodoHelper helper;

    public TodoService(TodoRepository todoDao, TodoHelper helper) {
        this.todoDao = todoDao;
        this.helper = helper;
    }

    @Override
    public Todo create(Todo todo) {
        helper.merge(todo);
        return todoDao.save(todo);
    }

    @Override
    public List<Todo> search(TodoFilter filter) {
        if (StringUtils.isEmpty(filter.getDescription())) {
            return todoDao.findAll();
        }
        return todoDao.findByDescriptionContaining(filter.getDescription());
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

package com.info.todobackend.service;

import com.info.todobackend.helper.TodoHelper;
import com.info.todobackend.model.todo.Todo;
import com.info.todobackend.model.todo.filter.TodoFilter;
import com.info.todobackend.repository.entityManager.TodoEmRepository;
import com.info.todobackend.repository.TodoRepository;
import com.info.todobackend.service.operations.TodoOperations;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService implements TodoOperations {

    private TodoRepository todoDao;
    private TodoEmRepository todoEmRepository;
    private TodoHelper helper;

    public TodoService(TodoRepository todoDao, TodoEmRepository todoEmRepository, TodoHelper helper) {
        this.todoDao = todoDao;
        this.todoEmRepository = todoEmRepository;
        this.helper = helper;
    }

    @Override
    public Todo create(Todo todo) {
        helper.merge(todo);
        return todoEmRepository.save(todo);
    }

    @Override
    public List<Todo> search(TodoFilter filter) {
        if (StringUtils.isEmpty(filter.getDescription())) {
            return todoEmRepository.getAll();
        }
        return todoEmRepository.search(filter);
    }

    @Override
    public Optional<Todo> getById(Long id) {
        return todoDao.findById(id);
    }

    @Override
    public Todo update(Todo todo) {
        return todoDao.save(todo);
    }

    public List<Todo> getTodoByProjectId(Long id) {
        return todoEmRepository.getTodoByProjectId(id);
    }

    @Override
    public void deleteById(Long id) {
        todoDao.deleteById(id);
    }
}

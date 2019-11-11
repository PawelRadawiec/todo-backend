package com.info.todobackend.controller;

import com.info.todobackend.model.Todo;
import com.info.todobackend.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity create(@RequestBody Todo todo) {
        return new ResponseEntity(todoService.create(todo), HttpStatus.OK);
    }

}

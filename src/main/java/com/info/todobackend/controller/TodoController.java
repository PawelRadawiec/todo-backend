package com.info.todobackend.controller;

import com.info.todobackend.model.Todo;
import com.info.todobackend.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping(value = "/todos")
    public ResponseEntity getAll() {
        return new ResponseEntity(todoService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return new ResponseEntity(todoService.getById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity update(@RequestBody Todo todo) {
        return new ResponseEntity(todoService.update(todo), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        todoService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

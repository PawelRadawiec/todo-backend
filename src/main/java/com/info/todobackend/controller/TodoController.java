package com.info.todobackend.controller;

import com.info.todobackend.model.todo.Todo;
import com.info.todobackend.model.todo.filter.TodoFilter;
import com.info.todobackend.service.operations.TodoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/todo")
public class TodoController {

    private TodoOperations todoService;

    public TodoController(TodoOperations todoService) {
        this.todoService = todoService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.create(todo), HttpStatus.OK);
    }

    @GetMapping(value = "/todos")
    public ResponseEntity<List<Todo>> getAll(
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String description
    ) {
        return new ResponseEntity<>(todoService.search(new TodoFilter(direction, sortBy, description)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return new ResponseEntity(todoService.getById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Todo> update(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.update(todo), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        todoService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

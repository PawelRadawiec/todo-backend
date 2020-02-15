package com.info.todobackend.controller;

import com.info.todobackend.model.todo.Todo;
import com.info.todobackend.model.todo.filter.TodoFilter;
import com.info.todobackend.service.operations.TodoOperations;
import com.info.todobackend.validators.TodoValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/todo")
public class TodoController {

    private TodoOperations todoService;
    private TodoValidator validator;

    public TodoController(TodoOperations todoService, TodoValidator validator) {
        this.todoService = todoService;
        this.validator = validator;
    }

    @InitBinder("todo")
    public void initMerchantOnlyBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Todo> create(@Valid @RequestBody Todo todo) {
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}

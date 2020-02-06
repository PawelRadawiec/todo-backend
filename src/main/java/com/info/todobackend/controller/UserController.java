package com.info.todobackend.controller;

import com.info.todobackend.model.SystemUser;
import com.info.todobackend.service.operations.UserOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    private UserOperations userOperations;

    public UserController(UserOperations userOperations) {
        this.userOperations = userOperations;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<SystemUser> create(@Valid @RequestBody SystemUser systemUser) {
        return new ResponseEntity<>(userOperations.create(systemUser), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<SystemUser> update(@Valid @RequestBody SystemUser systemUser) {
        return new ResponseEntity<>(userOperations.update(systemUser), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SystemUser> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userOperations.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<SystemUser>> search() {
        return new ResponseEntity<>(userOperations.searchUser(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        userOperations.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
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

package com.info.todobackend.controller;

import com.info.todobackend.model.User;
import com.info.todobackend.service.operations.UserOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/user")
public class UserController {

    private UserOperations userOperations;

    public UserController(UserOperations userOperations) {
        this.userOperations = userOperations;
    }

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody User user){
        return new ResponseEntity<>(userOperations.create(user), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<User> update(@RequestBody User user){
        return new ResponseEntity<>(userOperations.update(user), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userOperations.getById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<List<User>> search(){
        return new ResponseEntity<>(userOperations.searchUser(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        userOperations.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

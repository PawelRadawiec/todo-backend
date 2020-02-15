package com.info.todobackend.controller;

import com.info.todobackend.controller.generic.GenericController;
import com.info.todobackend.model.Activation;
import com.info.todobackend.model.SystemUser;
import com.info.todobackend.service.operations.UserOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController extends GenericController {

    private UserOperations userOperations;

    public UserController(UserOperations userOperations) {
        this.userOperations = userOperations;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<SystemUser> create(@RequestBody @Valid SystemUser systemUser) {
        return new ResponseEntity<>(userOperations.create(systemUser), HttpStatus.OK);
    }

    @PostMapping(value = "/activation")
    public ResponseEntity<Boolean> activate(@RequestBody Activation activation) {
        return new ResponseEntity<>(userOperations.activate(activation), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<SystemUser> update(@RequestBody @Valid SystemUser systemUser) {
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


}

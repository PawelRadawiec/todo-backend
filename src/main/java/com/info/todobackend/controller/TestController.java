package com.info.todobackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/hello/{name}")
    public ResponseEntity hello(@PathVariable("name") String name) {
        return new ResponseEntity(name, HttpStatus.OK);
    }
}

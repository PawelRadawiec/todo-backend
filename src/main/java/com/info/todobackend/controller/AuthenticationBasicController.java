package com.info.todobackend.controller;

import com.info.todobackend.model.AuthenticationBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthenticationBasicController {

    @GetMapping(value = "/basicauth")
    public AuthenticationBean basicAuth() {
        return new AuthenticationBean("you are authenticated");
    }

}

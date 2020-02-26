package com.info.todobackend.controller;

import com.info.todobackend.controller.generic.GenericController;
import com.info.todobackend.model.Project;
import com.info.todobackend.service.ProjectService;
import com.info.todobackend.validator.ProjectValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/project")
public class ProjectController extends GenericController {

    private ProjectService service;
    private ProjectValidator validator;

    public ProjectController(ProjectService service, ProjectValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @InitBinder("project")
    public void initMerchantOnlyBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping(value = "/create")
    public ResponseEntity create(@Valid  @RequestBody Project project) {
        return new ResponseEntity<>(service.save(project), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity search() {
        return new ResponseEntity<>(service.search(), HttpStatus.OK);
    }


}

package com.info.todobackend.service.operations;

import com.info.todobackend.model.Project;

import java.util.List;

public interface ProjectOperations {
    Project save(Project project);
    Project update(Project project);
    Project getById(Long id);
    List<Project> getAll();

}

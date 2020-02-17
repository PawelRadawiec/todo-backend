package com.info.todobackend.service;

import com.info.todobackend.model.Project;
import com.info.todobackend.repository.entityManager.ProjectRepository;
import com.info.todobackend.service.operations.ProjectOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements ProjectOperations {

    private ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Project save(Project project) {
        return repository.save(project);
    }

    @Override
    public Project update(Project project) {
        return null;
    }

    @Override
    public Project getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Project> getAll() {
        return null;
    }
}

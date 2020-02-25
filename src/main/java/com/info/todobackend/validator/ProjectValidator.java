package com.info.todobackend.validator;

import com.info.todobackend.model.Project;
import com.info.todobackend.repository.entityManager.ProjectRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProjectValidator implements Validator {

    private ProjectRepository repository;

    public ProjectValidator(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Project.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Project project = (Project) o;
        validateProject(project, errors);
        validateProjectNameUniqueness(project, errors);
    }

    private void validateProject(Project project, Errors errors) {
        if (StringUtils.isEmpty(project.getTitle())) {
            errors.rejectValue("title", "must_be_set", "must_be_set");
        }
        if (StringUtils.isEmpty(project.getDescription())) {
            errors.rejectValue("description", "must_be_set", "must_be_set");
        }
    }

    private void validateProjectNameUniqueness(Project project, Errors errors) {
        Optional<Project> projectByTitle = repository.findByTitle(project.getTitle());
        if (projectByTitle.isPresent()) {
            errors.rejectValue("title", "must_be_unique", "must_be_unique");
        }
    }

}

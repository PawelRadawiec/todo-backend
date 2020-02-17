package com.info.todobackend.repository.entityManager;

import com.info.todobackend.model.Project;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Repository
public class ProjectRepository {

    private EntityManager em;
    private TodoEmRepository todoEm;

    public ProjectRepository(EntityManager em, TodoEmRepository todoEm) {
        this.em = em;
        this.todoEm = todoEm;
    }

    public Project save(Project project) {
        em.persist(project);
        return findById(project.getId());
    }

    public Project findById(Long id) {
        return em.find(Project.class, id);
    }


}

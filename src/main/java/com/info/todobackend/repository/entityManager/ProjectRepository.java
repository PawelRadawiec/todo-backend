package com.info.todobackend.repository.entityManager;

import com.info.todobackend.model.Project;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

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

    public Optional<Project> findByTitle(String title) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Project> cq = builder.createQuery(Project.class);
        Root<Project> root = cq.from(Project.class);
        cq.where(
                builder.equal(root.get("title"), title)
        );
        TypedQuery<Project> query = em.createQuery(cq);
        return query.getResultList().stream().findFirst();
    }

    public Project findById(Long id) {
        return em.find(Project.class, id);
    }


}

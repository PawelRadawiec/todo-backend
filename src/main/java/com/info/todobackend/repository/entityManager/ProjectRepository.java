package com.info.todobackend.repository.entityManager;

import com.info.todobackend.model.Project;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class ProjectRepository {

    private EntityManager em;

    public ProjectRepository(EntityManager em) {
        this.em = em;
    }

    public Project save(Project project) {
        em.persist(project);
        return findById(project.getId()).orElseGet(Project::new);
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

    public Optional<Project> findById(Long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Project> cq = builder.createQuery(Project.class);
        Root<Project> root = cq.from(Project.class);
        cq.where(
                builder.equal(root.get("id"), id)
        );
        TypedQuery<Project> query = em.createQuery(cq);
        return query.getResultList().stream().findFirst();
    }

    public List<Project> search() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = builder.createQuery(Project.class);
        Root<Project> root = criteriaQuery.from(Project.class);

        TypedQuery<Project> query = em.createQuery(criteriaQuery.select(root));
        return query.getResultList();
    }


}

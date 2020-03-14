package com.info.todobackend.repository.entityManager;

import com.info.todobackend.model.Project;
import com.info.todobackend.model.todo.Todo;
import com.info.todobackend.model.todo.filter.TodoFilter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class TodoEmRepository {

    private EntityManager em;

    public TodoEmRepository(EntityManager em) {
        this.em = em;
    }

    public Todo save(Todo todo) {
        em.persist(todo);
        return todo;
    }

    public Todo findById(Long id) {
        return em.find(Todo.class, id);
    }

    public List<Todo> getAll() {
        TypedQuery<Todo> query = em.createQuery("select t from Todo t", Todo.class);
        return query.getResultList();
    }

    public List<Todo> search(TodoFilter filter) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Todo> criteriaQuery = builder.createQuery(Todo.class);
        Root<Todo> root = criteriaQuery.from(Todo.class);
        Predicate like = builder.like(root.get("description"), "%".concat(filter.getDescription()).concat("%"));
        criteriaQuery.where(like);

        return em.createQuery(criteriaQuery.select(root)).getResultList();
    }

    public List<Todo> getTodoByProjectId(Long id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Todo> criteriaQuery = criteriaBuilder.createQuery(Todo.class);
        Root<Project> projectRoot = criteriaQuery.from(Project.class);
        Join<Project, Todo> projectTodoJoin = projectRoot.join("todos");
        criteriaQuery.select(projectTodoJoin).where(
                criteriaBuilder.equal(projectRoot.get("id"), id)
        );
        criteriaQuery.orderBy(criteriaBuilder.asc(projectTodoJoin.get("createdAt")));

        return em.createQuery(criteriaQuery).getResultList();
    }

    public Optional<Todo> findByTitle(String title) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Todo> criteriaQuery = criteriaBuilder.createQuery(Todo.class);
        Root<Todo> root = criteriaQuery.from(Todo.class);
        criteriaQuery.where(
                criteriaBuilder.equal(root.get("title"), title)
        );
        return em.createQuery(criteriaQuery).getResultList().stream().findFirst();
    }

    public Optional<Todo> findByTitleAndProjectId(String title, Long projectId) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Todo> criteriaQuery = criteriaBuilder.createQuery(Todo.class);
        Root<Project> projectRoot = criteriaQuery.from(Project.class);
        Join<Project, Todo> projectTodoJoin = projectRoot.join("todos");
        criteriaQuery.select(projectTodoJoin).where(criteriaBuilder.equal(projectRoot.get("id"), projectId));
        criteriaQuery.where(
                criteriaBuilder.equal(projectTodoJoin.get("title"), title)
        );
        return em.createQuery(criteriaQuery).getResultList().stream().findFirst();
    }


}

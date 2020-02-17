package com.info.todobackend.repository.entityManager;

import com.info.todobackend.model.todo.Todo;
import com.info.todobackend.model.todo.filter.TodoFilter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
@Transactional
public class TodoEmRepository {

    private EntityManager em;
    private SubtaskEmRepository subtaskEmRepository;

    public TodoEmRepository(EntityManager em, SubtaskEmRepository subtaskEmRepository) {
        this.em = em;
        this.subtaskEmRepository = subtaskEmRepository;
    }

    public Todo save(Todo todo) {
        todo.getSubtasks().forEach(subtask -> subtaskEmRepository.save(subtask, todo));
        em.persist(todo);
        return todo;
    }

    public Todo findById(Long id) {
        return em.find(Todo.class, id);
    }

    public List<Todo> getAll() {
        TypedQuery<Todo> query = em.createQuery("select t from Todo", Todo.class);
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


}

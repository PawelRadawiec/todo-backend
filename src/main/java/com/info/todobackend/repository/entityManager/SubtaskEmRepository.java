package com.info.todobackend.repository.entityManager;

import com.info.todobackend.model.todo.Subtask;
import com.info.todobackend.model.todo.Todo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class SubtaskEmRepository {

    private EntityManager em;

    public SubtaskEmRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Subtask subtask) {
        em.persist(subtask);
    }

    public void save(Subtask subtask, Todo todo) {
        subtask.setTodo(todo);
        em.persist(subtask);
    }

}

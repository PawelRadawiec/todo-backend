package com.info.todobackend.repository.entityManager;

import com.info.todobackend.model.todo.Todo;
import com.info.todobackend.repository.entityManager.SubtaskEmRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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

}

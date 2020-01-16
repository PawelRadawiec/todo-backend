package com.info.todobackend.repository;

import com.info.todobackend.model.todo.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
}

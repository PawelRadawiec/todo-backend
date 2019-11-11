package com.info.todobackend.repository;

import com.info.todobackend.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository  extends JpaRepository<Todo, Long> {
}

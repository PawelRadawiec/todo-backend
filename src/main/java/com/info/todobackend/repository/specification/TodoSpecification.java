package com.info.todobackend.repository.specification;

import com.info.todobackend.model.todo.Todo;
import com.info.todobackend.model.todo.Todo_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TodoSpecification  {
    public static Specification<Todo> getTodoByDescriptionSpec(String description) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Todo_.description), description);
    }
}

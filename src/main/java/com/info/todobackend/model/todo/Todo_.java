package com.info.todobackend.model.todo;

import com.info.todobackend.model.todo.Todo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Todo.class)
public class Todo_ {
    public static volatile SingularAttribute<Todo, String> description;
}

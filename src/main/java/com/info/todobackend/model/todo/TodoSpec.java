package com.info.todobackend.model.todo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Todo.class)
public class TodoSpec {
    public static volatile SingularAttribute<Todo, String> description;
}

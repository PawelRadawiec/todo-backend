package com.info.todobackend.model.todo.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TodoFilter {
    private String direction;
    private String sortBy;
}

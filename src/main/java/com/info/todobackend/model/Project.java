package com.info.todobackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.info.todobackend.model.todo.InformationModel;
import com.info.todobackend.model.todo.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project extends InformationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference
    private List<Todo> todos = new ArrayList<>();

    private String author;

}

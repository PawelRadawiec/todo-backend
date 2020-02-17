package com.info.todobackend.model.todo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "todo_subtask")
@Getter
@Setter
@NoArgsConstructor
public class Subtask extends InformationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String content;

    @ManyToOne()
    @JsonBackReference
    private Todo todo;


}

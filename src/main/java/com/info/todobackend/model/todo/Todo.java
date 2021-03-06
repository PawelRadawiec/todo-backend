package com.info.todobackend.model.todo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.info.todobackend.model.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "todos")
@Getter
@Setter
@NoArgsConstructor
public class Todo extends InformationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Boolean done;

    @Column
    private String status;

    @Column(name = "target_date")
    private LocalDate targetDate;

    @Column
    private String author;

    @OneToMany(
            mappedBy = "todo",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Subtask> subtasks;

    @ManyToOne
    @JsonBackReference
    private Project project;

}

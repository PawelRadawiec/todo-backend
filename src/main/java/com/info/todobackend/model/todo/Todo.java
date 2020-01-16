package com.info.todobackend.model.todo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "todos")
@Getter @Setter
@NoArgsConstructor
public class Todo extends InformationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "todo_id", unique = true, nullable = false)
    private Long id;

    @Column
    private String description;

    @Column
    private Boolean done;

    @Column(name = "target_type")
    private LocalDate targetType;

    @Column
    private String author;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "todo"
    )
    @JsonManagedReference
    private Set<Subtask> subtasks;

}

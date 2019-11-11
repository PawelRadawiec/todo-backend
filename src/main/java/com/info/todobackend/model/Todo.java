package com.info.todobackend.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String description;

    @Column
    private Boolean done;

    @Column(name = "target_type")
    private LocalDate targetType;

    public Todo(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public LocalDate getTargetType() {
        return targetType;
    }

    public void setTargetType(LocalDate targetType) {
        this.targetType = targetType;
    }
}

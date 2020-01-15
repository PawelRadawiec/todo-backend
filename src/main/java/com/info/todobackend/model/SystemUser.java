package com.info.todobackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "system_user")
@Getter @Setter @NoArgsConstructor
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String login;

    @Column(unique = true)
    private String email;

    @Column()
    private String password;

}

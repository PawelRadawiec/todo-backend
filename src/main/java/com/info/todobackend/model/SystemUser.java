package com.info.todobackend.model;

import com.info.todobackend.validator.ValidEmail;
import com.info.todobackend.validator.ValidLogin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "system_user")
@Getter @Setter @NoArgsConstructor
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty
    @ValidLogin
    @Column(unique = true)
    private String login;

    @NotEmpty
    @ValidEmail
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Column()
    private String password;

    @Column(name = "activation_column")
    private String activationCode;

    @Column
    private Boolean active;

}

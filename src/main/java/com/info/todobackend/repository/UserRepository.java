package com.info.todobackend.repository;

import com.info.todobackend.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SystemUser, Long> {

    SystemUser findSystemUserByLogin(@Param("login") String login);
    SystemUser findByEmail(@Param("email") String email);
    SystemUser findByActivationCode(@Param("activationCode") String activationCode);
    SystemUser findByLogin(@Param("login") String login);

}

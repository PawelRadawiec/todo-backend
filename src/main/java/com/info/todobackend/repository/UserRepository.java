package com.info.todobackend.repository;

import com.info.todobackend.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SystemUser, Long> {
}

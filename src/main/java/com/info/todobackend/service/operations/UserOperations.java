package com.info.todobackend.service.operations;

import com.info.todobackend.model.Activation;
import com.info.todobackend.model.SystemUser;

import java.util.List;

public interface UserOperations {
    SystemUser create(SystemUser systemUser);
    SystemUser update(SystemUser systemUser);
    SystemUser getById(Long id);
    SystemUser findByLogin(String login);
    Boolean activate(Activation activation);
    List<SystemUser> searchUser();
    void delete(Long id);
}

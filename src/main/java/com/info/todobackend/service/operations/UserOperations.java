package com.info.todobackend.service.operations;

import com.info.todobackend.model.SystemUser;

import java.util.List;

public interface UserOperations {
    SystemUser create(SystemUser systemUser);
    SystemUser update(SystemUser systemUser);
    SystemUser getById(Long id);
    List<SystemUser> searchUser();
    void delete(Long id);
}

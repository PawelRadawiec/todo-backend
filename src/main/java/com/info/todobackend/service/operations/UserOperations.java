package com.info.todobackend.service.operations;

import com.info.todobackend.model.User;

import java.util.List;

public interface UserOperations {
    User create(User user);
    User update(User user);
    User getById(Long id);
    List<User> searchUser();
    void delete(Long id);
}

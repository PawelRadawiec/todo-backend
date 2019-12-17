package com.info.todobackend.service;

import com.info.todobackend.model.User;
import com.info.todobackend.repository.UserRepository;
import com.info.todobackend.service.operations.UserOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserOperations {

    private UserRepository userDao;

    public UserService(UserRepository userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(User user) {
        return userDao.save(user);
    }

    @Override
    public User update(User user) {
        return userDao.save(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.getOne(id);
    }

    @Override
    public List<User> searchUser() {
        return userDao.findAll();
    }

    @Override
    public void delete(Long id) {
        if(id != null) {
            userDao.deleteById(id);
        }
    }
}

package com.info.todobackend.service;

import com.info.todobackend.model.SystemUser;
import com.info.todobackend.repository.UserRepository;
import com.info.todobackend.service.operations.UserOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserOperations {

    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SystemUser create(SystemUser systemUser) {
        systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        return userDao.save(systemUser);
    }

    @Override
    public SystemUser update(SystemUser systemUser) {
        return userDao.save(systemUser);
    }

    @Override
    public SystemUser getById(Long id) {
        return userDao.getOne(id);
    }

    @Override
    public List<SystemUser> searchUser() {
        return userDao.findAll();
    }

    @Override
    public void delete(Long id) {
        if(id != null) {
            userDao.deleteById(id);
        }
    }
}

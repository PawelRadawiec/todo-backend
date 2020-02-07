package com.info.todobackend.service;

import com.info.todobackend.helper.SystemUserHelper;
import com.info.todobackend.model.SystemUser;
import com.info.todobackend.repository.UserRepository;
import com.info.todobackend.service.operations.UserOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserOperations {

    private UserRepository userDao;
    private SystemUserHelper userHelper;

    public UserService(UserRepository userDao, SystemUserHelper userHelper) {
        this.userDao = userDao;
        this.userHelper = userHelper;
    }

    @Override
    public SystemUser create(SystemUser systemUser) {
        userHelper.merge(systemUser);
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

package com.info.todobackend.service;

import com.info.todobackend.helper.SystemUserHelper;
import com.info.todobackend.model.Activation;
import com.info.todobackend.model.SystemUser;
import com.info.todobackend.model.helper.EmailHelper;
import com.info.todobackend.repository.UserRepository;
import com.info.todobackend.service.operations.UserOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class UserService implements UserOperations {

    private UserRepository userDao;
    private SystemUserHelper userHelper;
    private EmailService emailService;

    public UserService(UserRepository userDao, SystemUserHelper userHelper, EmailService emailService) {
        this.userDao = userDao;
        this.userHelper = userHelper;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public SystemUser create(SystemUser systemUser) {
        userHelper.merge(systemUser);
        SystemUser savedUser = userDao.save(systemUser);
        try {
            emailService.senEmail(
                    EmailHelper.prepareRegistrationMail(savedUser),
                    EmailHelper.prepareRegistrationContext(savedUser)
            );
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return savedUser;
    }

    @Override
    public SystemUser update(SystemUser systemUser) {
        return userDao.save(systemUser);
    }

    @Override
    public Boolean activate(Activation activation) {
        SystemUser user = userDao.findByActivationCode(activation.getActivationCode());
        Boolean exist = (user != null);
        if (exist) {
            user.setActive(true);
            update(user);
        }
        return exist;
    }

    @Override
    public SystemUser getById(Long id) {
        return userDao.getOne(id);
    }

    @Override
    public SystemUser findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public List<SystemUser> searchUser() {
        return userDao.findAll();
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            userDao.deleteById(id);
        }
    }
}

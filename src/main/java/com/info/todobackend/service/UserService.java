package com.info.todobackend.service;

import com.info.todobackend.helper.SystemUserHelper;
import com.info.todobackend.model.EmailDto;
import com.info.todobackend.model.SystemUser;
import com.info.todobackend.repository.UserRepository;
import com.info.todobackend.service.operations.UserOperations;
import org.springframework.stereotype.Service;

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
    public SystemUser create(SystemUser systemUser) {
        userHelper.merge(systemUser);
        // just for test purpose
        // SystemUser savedUser = userDao.save(systemUser);
        EmailDto dto = new EmailDto();
        dto.setTo(systemUser.getEmail());
        dto.setSubject("subject");
        dto.setFrom("todo@app");
        dto.setTemplateName("registration");
        try {
            emailService.senEmail(dto);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return systemUser;
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
        if (id != null) {
            userDao.deleteById(id);
        }
    }
}

package com.info.todobackend.helper;

import com.info.todobackend.model.SystemUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class SystemUserHelper {

    private PasswordEncoder passwordEncoder;

    public SystemUserHelper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void merge(SystemUser user) {
        user.setActive(false);
        user.setActivationCode(RandomStringUtils.random(20, false, true));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }


}

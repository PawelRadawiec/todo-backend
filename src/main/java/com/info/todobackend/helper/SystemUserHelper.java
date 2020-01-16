package com.info.todobackend.helper;

import com.info.todobackend.model.SystemUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SystemUserHelper {

    private PasswordEncoder passwordEncoder;

    public SystemUserHelper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void merge(SystemUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }


}

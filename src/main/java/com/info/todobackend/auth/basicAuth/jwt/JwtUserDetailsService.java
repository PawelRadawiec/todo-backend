package com.info.todobackend.auth.basicAuth.jwt;

import com.info.todobackend.model.SystemUser;
import com.info.todobackend.model.helper.JwtUserDetailsHelper;
import com.info.todobackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<SystemUser> currentUser = Optional.ofNullable(userRepository.findSystemUserByLogin(username));
        JwtUserDetails userDetails = null;
        if (currentUser.isPresent()) {
            userDetails = JwtUserDetailsHelper.createJwtUserDetails(currentUser.get());
        }
        return userDetails;
    }
}

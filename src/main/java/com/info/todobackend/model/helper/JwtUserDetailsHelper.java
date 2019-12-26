package com.info.todobackend.model.helper;

import com.info.todobackend.auth.basicAuth.jwt.JwtUserDetails;
import com.info.todobackend.model.SystemUser;

public class JwtUserDetailsHelper {
    private JwtUserDetailsHelper() {
    }

    public static JwtUserDetails createJwtUserDetails(SystemUser user) {
        return new JwtUserDetails(
                user.getId(), user.getLogin(),
                user.getPassword(), "ROLE_USER_2"
        );

    }
}

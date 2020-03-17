package com.info.todobackend.auth.basicAuth.jwt.resource;

import com.info.todobackend.model.SystemUser;

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {

    private static final long serialVersionUID = 8317676219297719109L;

    private final String token;

    private SystemUser user;

    public JwtTokenResponse(String token) {
        this.token = token;
    }

    public JwtTokenResponse(String token, SystemUser user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return this.token;
    }

    public SystemUser getUser() { return user; }
}

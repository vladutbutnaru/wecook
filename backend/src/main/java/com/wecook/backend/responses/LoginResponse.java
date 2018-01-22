package com.wecook.backend.responses;

import com.wecook.backend.models.Token;

public class LoginResponse {

    private Token token;
    private String status;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token=" + token +
                ", status='" + status + '\'' +
                '}';
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

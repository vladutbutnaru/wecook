package com.wecook.backend.models;

import java.sql.Timestamp;

public class Token {
    private int id;
    private User user;
    private Timestamp createdAt;
    private int status;
    private Timestamp refreshedAt;
    private String code;

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", refreshedAt=" + refreshedAt +
                ", code='" + code + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getRefreshedAt() {
        return refreshedAt;
    }

    public void setRefreshedAt(Timestamp refreshedAt) {
        this.refreshedAt = refreshedAt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

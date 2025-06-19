package com.example.stocker.domain;

import java.sql.Timestamp;

public class User {
    private Integer id;
    private String name;
    private String mail;
    private String password;
    private boolean adminFlg;
    private boolean delFlg;
    private Timestamp createdAt;
    private Timestamp updateAt;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isAdminFlg() {
        return adminFlg;
    }
    public void setAdminFlg(boolean adminFlg) {
        this.adminFlg = adminFlg;
    }
    public boolean isDelFlg() {
        return delFlg;
    }
    public void setDelFlg(boolean delFlg) {
        this.delFlg = delFlg;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public Timestamp getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }
}

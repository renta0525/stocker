package com.example.stocker.domain;

public class Shop {
    private Integer id;
    private String name;
    private Integer userId;
    private String imagePath;
    private boolean delFlg;

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
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public boolean isDelFlg() {
        return delFlg;
    }
    public void setDelFlg(boolean delFlg) {
        this.delFlg = delFlg;
    }
}

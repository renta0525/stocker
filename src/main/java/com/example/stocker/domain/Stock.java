package com.example.stocker.domain;

public class Stock {
    private Integer id;
    private String name;
    private Integer price;
    private Integer quantity;
    private Integer shopId;
    private Integer categoryId;
    private String memo;
    private Integer boundaryValue;
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
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getShopId() {
        return shopId;
    }
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public Integer getBoundaryValue() {
        return boundaryValue;
    }
    public void setBoundaryValue(Integer boundaryValue) {
        this.boundaryValue = boundaryValue;
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

package com.example.stocker.domain;

public class Order {
    private Integer id;
    private Integer stockId;
    private Integer quantity;
    private String userName;
    private Integer totalPrice;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getStockId() {
        return stockId;
    }
    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", stockId=" + stockId + ", quantity=" + quantity + ", userName=" + userName
                + ", totalPrice=" + totalPrice + "]";
    }
}

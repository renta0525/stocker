package com.example.stocker.event;

public class PurchaseEvent {
    private int stockId;
    private String itemName;
    private String userName;
    private int quantity;

    public PurchaseEvent () {

    }

    public PurchaseEvent(int stockId, String itemName, String userName, int quantity) {
        this.stockId = stockId;
        this.itemName = itemName;
        this.userName = userName;
        this.quantity = quantity;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}

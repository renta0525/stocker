package com.example.stocker.event;

public class LowStockEvent {
    private int stockId;
    private String message;

    public LowStockEvent () {
    }

    public LowStockEvent (int stockId, String message) {
        this.stockId = stockId;
        this.message = message;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LowStockEvent [stockId=" + stockId + ", message=" + message + "]";
    }
}

package com.example.stocker.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.stocker.event.LowStockEvent;

/**
 * 送信用クラス
 */
@Service
public class StockNotificationProducer {
    
    @Autowired
    private KafkaTemplate<String, LowStockEvent> kafkaTemplate;

    public void sendLowStockNotification(int stockId, String message) {
        LowStockEvent event = new LowStockEvent(stockId, message);
        kafkaTemplate.send("low-stock-topic", event);
        System.out.println("📤 Kafka送信: " + event);
    }
}

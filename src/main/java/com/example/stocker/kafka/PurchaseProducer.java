package com.example.stocker.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.stocker.event.PurchaseEvent;

@Service
public class PurchaseProducer {
    
    private static final String TOPIC = "purchase-topic";
    
    @Autowired
    private KafkaTemplate<String, PurchaseEvent> kafkaTemplate;

    public void send(PurchaseEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("✅ Kafkaへ送信: " + event.getItemName() + " を " + event.getUserName() + " が購入");
    }
}

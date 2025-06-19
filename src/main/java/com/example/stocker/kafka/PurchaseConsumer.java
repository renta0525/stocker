package com.example.stocker.kafka;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.stocker.domain.MessageHistory;
import com.example.stocker.event.PurchaseEvent;
import com.example.stocker.repository.MessageHistoryRepository;
import com.example.stocker.service.SlackNotificationService;

@Service
public class PurchaseConsumer {
    
    @Autowired
    private MessageHistoryRepository messageHistoryRepository;
    @Autowired
    private SlackNotificationService slackNotificationService;

    @KafkaListener(topics = "purchase-topic", groupId = "purchase-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen (PurchaseEvent event) {
        System.out.println("📩 Kafka 受信: " + event.getItemName() + " を " + event.getUserName() + " が " + event.getQuantity() + "個購入");
        String message = String.format("【購入通知】%s が %d個購入されました（購入者：%s）", event.getItemName(), event.getQuantity(), event.getUserName());

        MessageHistory history = new MessageHistory();
        history.setStockId(event.getStockId());
        history.setMessage(message);
        history.setCreatedAt(LocalDateTime.now());

        messageHistoryRepository.insert(history);

        slackNotificationService.sendSlackMessage(message);

        System.out.println("✅ Kafka処理完了: DB保存 & Slack通知");
    }
}

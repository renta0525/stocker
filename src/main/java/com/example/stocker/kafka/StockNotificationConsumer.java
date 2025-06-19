package com.example.stocker.kafka;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.stocker.domain.MessageHistory;
import com.example.stocker.event.LowStockEvent;
import com.example.stocker.repository.MessageHistoryRepository;
import com.example.stocker.service.SlackNotificationService;

@Service
public class StockNotificationConsumer {

    @Autowired
    private MessageHistoryRepository messageHistoryRepository;

    @Autowired
    private SlackNotificationService slackNotificationService;

    @KafkaListener(topics = "low-stock-topic", groupId = "low-stock-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(LowStockEvent event) {
        System.out.println("📩 Kafka受信（在庫不足）: " + event.getMessage());

        // DB保存
        MessageHistory history = new MessageHistory();
        history.setStockId(event.getStockId());
        history.setMessage(event.getMessage());
        history.setCreatedAt(LocalDateTime.now());
        messageHistoryRepository.insert(history);

        // Slack通知
        slackNotificationService.sendSlackMessage(event.getMessage());

        System.out.println("✅ Kafka処理完了: 在庫不足通知をDB保存＆Slack通知");
    }
}

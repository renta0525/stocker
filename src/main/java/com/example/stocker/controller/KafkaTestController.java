// package com.example.stocker.controller;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.stocker.kafka.StockNotificationProducer;

// @RestController
// @RequestMapping("/test-kafka")
// public class KafkaTestController {

//     private final StockNotificationProducer producer;

//     public KafkaTestController(StockNotificationProducer producer) {
//         this.producer = producer;
//     }

//     @GetMapping("/send")
//     public String sendTestMessage() {
//         producer.sendLowStockNotification(3, "在庫警告：商品ID 3 の在庫が2個になりました");
//         return "メッセージ送信完了！";
//     }

//     @GetMapping("/send-notebook")
//     public String sendNotebookMessage() {
//         producer.sendLowStockNotification(5, "在庫警告：ノートの在庫が2個になりました（閾値: 5）");
//         return "ノートの在庫警告を送信しました！";
//     }
// }

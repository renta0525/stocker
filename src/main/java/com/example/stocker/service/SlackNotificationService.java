package com.example.stocker.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 * Slackに通知用
 */
@Service
public class SlackNotificationService {
    
    @Value("${notification.slack.webhook-url}")
    private String webhookUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendSlackMessage (String message) {
        Map<String, String> payload = new HashMap<>();
        payload.put("text", message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        try {
            restTemplate.postForEntity(webhookUrl, request, String.class);
             System.out.println("✅ Slackに通知を送信しました");
        } catch (Exception e) {
            System.err.println("❌ Slack送信エラー: " + e.getMessage());
        }
    }
}

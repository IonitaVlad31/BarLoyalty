package com.barLoyalty.gateway.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendBalanceUpdate(Long userId, Integer newBalance) {
        // sends to: /topic/balance/{userId}
        messagingTemplate.convertAndSend("/topic/balance/" + userId, newBalance);

        System.out.println("Notificare trimisa catre user " + userId + ": Sold nou " + newBalance);
    }
}
package com.barLoyalty.gateway.service;

import com.barLoyalty.gateway.dto.TransactionRequest;
import com.barLoyalty.gateway.entity.Transaction;
import com.barLoyalty.gateway.entity.TransactionType;
import com.barLoyalty.gateway.entity.User;
import com.barLoyalty.gateway.repository.TransactionRepository;
import com.barLoyalty.gateway.entity.*;
import com.barLoyalty.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public String processTransaction(TransactionRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Utilizator negasit!"));

        TransactionType type = TransactionType.valueOf(request.getType().toUpperCase());

        if (type == TransactionType.EARN) {
            user.setCurrentPoints(user.getCurrentPoints() + request.getAmount());
        } else if (type == TransactionType.REDEEM) {
            if (user.getCurrentPoints() < request.getAmount()) {
                throw new RuntimeException("Fonduri insuficiente! Ai doar " + user.getCurrentPoints() + " puncte.");
            }
            user.setCurrentPoints(user.getCurrentPoints() - request.getAmount());
        }

        userRepository.save(user);

        Transaction transaction = Transaction.builder()
                .user(user)
                .amount(request.getAmount())
                .type(type)
                .qrCodeHash(request.getQrCodeHash())
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        try {
            notificationService.sendBalanceUpdate(user.getId(), user.getCurrentPoints());
        } catch (Exception e) {
            System.err.println("Eroare la trimiterea notificarii WebSocket: " + e.getMessage());
        }
        return "Succes! Sold nou: " + user.getCurrentPoints();
    }
}

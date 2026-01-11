package com.barLoyalty.gateway.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bar_id")
    private Bar bar;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // EARN sau REDEEM

    private Integer amount;

    private String qrCodeHash;

    private LocalDateTime timestamp = LocalDateTime.now();
}

enum TransactionType {
    EARN,
    REDEEM
}

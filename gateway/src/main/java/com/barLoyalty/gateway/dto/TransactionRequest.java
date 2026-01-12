package com.barLoyalty.gateway.dto;

import lombok.Data;

@Data
public class TransactionRequest {
    private Long userId;
    private Integer amount;
    private String type; // EARN sau REDEEM
    private String qrCodeHash;
}

package com.example.dto;

import lombok.Data;

@Data
public class TransferFastTagBalanceRequest {
    private String senderFastTagId;
    private String receiverFastTagId;
    private Double transferAmount;
}

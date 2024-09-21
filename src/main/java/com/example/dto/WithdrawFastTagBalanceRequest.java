package com.example.dto;

import lombok.Data;

@Data
public class WithdrawFastTagBalanceRequest {
    private Double amount;
    private String customerId;
    private String fastTagId;
}

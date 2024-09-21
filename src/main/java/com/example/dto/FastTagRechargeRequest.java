package com.example.dto;

import com.example.enums.PaymentMode;
import lombok.Data;

@Data
public class FastTagRechargeRequest {
    private String fastTagId;
    private Double amount;
    private PaymentMode paymentMode;
}

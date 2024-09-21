package com.example.dto;

import lombok.Data;

@Data
public class FastTagTollPaymentRequest {

    private String fastTagId;
    private Double amount;
}

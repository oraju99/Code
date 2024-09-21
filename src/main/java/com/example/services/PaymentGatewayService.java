package com.example.services;

import com.example.dto.PaymentGatewayRequest;
import com.example.enums.ResponseStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayService {
    public ResponseStatus makePayment(PaymentGatewayRequest paymentGatewayRequest) {

        return ResponseStatus.SUCCESS;
    }
}

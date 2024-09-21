package com.example.utils;

import com.example.dto.FastTagRechargeRequest;
import com.example.dto.PaymentGatewayRequest;
import com.example.dto.WithdrawFastTagBalanceRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayRequestGenerator {
    public PaymentGatewayRequest generateFastTagRechargeRequest(FastTagRechargeRequest fastTagRechargeRequest) {
        // Logic to generate all the data required for processing payment on the payment gateway for fast tag recharge
        return new PaymentGatewayRequest();
    }

    public PaymentGatewayRequest generateWithdrawFastTagBalanceRequest(
            WithdrawFastTagBalanceRequest withdrawFastTagBalanceRequest) {
        // Logic to generate all the data required for processing payment on the payment gateway for fast tag balance withdraw
        return new PaymentGatewayRequest();
    }
}

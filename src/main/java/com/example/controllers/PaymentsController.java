package com.example.controllers;

import com.example.dto.FastTagRechargeRequest;
import com.example.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/payments")
@RequiredArgsConstructor
public class PaymentsController {

    private final PaymentService paymentService;

    // Controller to recharge the fast tag
    @PostMapping("/recharge")
    public String rechargeFastTag(FastTagRechargeRequest fastTagRechargeRequest) throws Exception {
        return paymentService.rechargeFastTag(fastTagRechargeRequest);
    }

}

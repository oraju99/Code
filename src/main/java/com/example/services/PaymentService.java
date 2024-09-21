package com.example.services;

import com.example.dto.FastTag;
import com.example.dto.FastTagRechargeRequest;
import com.example.dto.PaymentGatewayRequest;
import com.example.enums.ResponseStatus;
import com.example.utils.PaymentGatewayRequestGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentGatewayService paymentGatewayService;

    private final PaymentGatewayRequestGenerator paymentGatewayRequestGenerator;

    private final FastTagService fastTagService;

    public String rechargeFastTag(FastTagRechargeRequest fastTagRechargeRequest) throws Exception {

        // validate the input of the fast tag recharge
        if(!validateFastTagRechargeRequest(fastTagRechargeRequest)) {
            throw new Exception("Invalid fast tag request");
        }

        boolean paymentSuccess = false;

        PaymentGatewayRequest paymentGatewayRequest =
                paymentGatewayRequestGenerator.generateFastTagRechargeRequest(fastTagRechargeRequest);

        try {
            ResponseStatus paymentGatewayResponse = paymentGatewayService.makePayment(paymentGatewayRequest);
            if (paymentGatewayResponse.equals(ResponseStatus.SUCCESS)) {
                paymentSuccess = true;
            } else {
                throw new Exception("Payment failed at gateway");
            }
        } catch (Exception e) {
            throw new Exception("Payment gateway is down");
        }

        if(paymentSuccess) {
            fastTagService.updateFastTagBalanceDetailByFastTagId(fastTagRechargeRequest.getFastTagId(),
                    fastTagRechargeRequest.getAmount());
            return "Recharge Successful";
        } else {
            return "Recharge unsuccessful";
        }
    }

    private boolean validateFastTagRechargeRequest(FastTagRechargeRequest fastTagRechargeRequest) {
        return true;
    }

    private PaymentGatewayRequest generatePaymentGatewayRequest(FastTagRechargeRequest fastTagRechargeRequest) {
        // Logic to generate all the data required for processing payment on the payment gateway
        return new PaymentGatewayRequest();
    }
}

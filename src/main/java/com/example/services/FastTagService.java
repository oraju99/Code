package com.example.services;

import com.example.dto.*;
import com.example.enums.FastTagStatus;
import com.example.enums.ResponseStatus;
import com.example.utils.PaymentGatewayRequestGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FastTagService {

    private final PaymentGatewayService paymentGatewayService;

    private final PaymentGatewayRequestGenerator paymentGatewayRequestGenerator;

    private List<FastTag> fastTagData = new ArrayList<>();

    public String withdrawFastTagBalance(WithdrawFastTagBalanceRequest withdrawFastTagBalanceRequest) throws Exception {
        // method to validate the incoming request
        withdrawFastTagBalanceRequestValidation(withdrawFastTagBalanceRequest);

        boolean transferSuccess = false;
        PaymentGatewayRequest paymentGatewayRequest = paymentGatewayRequestGenerator
                .generateWithdrawFastTagBalanceRequest(withdrawFastTagBalanceRequest);

        // this method transfers the money from fast tag to current linked bank account of the customer
        transferMoneyToAccount(paymentGatewayRequest);

        updateFastTagBalanceDetailByFastTagId(withdrawFastTagBalanceRequest.getFastTagId(),
                (-1)*withdrawFastTagBalanceRequest.getAmount());

        return "Money withdrawn successfully";
    }

    private void transferMoneyToAccount(PaymentGatewayRequest paymentGatewayRequest) throws Exception {
        boolean transferSuccess;
        try {
            ResponseStatus paymentGatewayResponse = paymentGatewayService.makePayment(paymentGatewayRequest);
            if (paymentGatewayResponse.equals(ResponseStatus.SUCCESS)) {
                transferSuccess = true;
            } else {
                throw new Exception("Transfer failed at gateway, please try again after some time");
            }
        } catch (Exception e) {
            throw new Exception("Payment gateway is down, please try again after some time");
        }
    }

    private void withdrawFastTagBalanceRequestValidation(WithdrawFastTagBalanceRequest request) throws Exception {
        if(StringUtils.isEmpty(request.getCustomerId())) {
            throw new Exception("Invalid customerId");
        }
    }

    public Double fetchFastTagBalance(String fastTagId) throws Exception {
        FastTag fastTag = fetchFastTagDetailsByFastTagId(fastTagId);
        if (fastTag == null) {
            throw new Exception("No fast tag data available with fast tag ID : " + fastTagId);
        } else {
            return fastTag.getFastTagBalance();
        }
    }

    public FastTagStatus fetchFastTagStatus(String fastTagId) throws Exception {
        FastTag fastTag = fetchFastTagDetailsByFastTagId(fastTagId);
        if (fastTag == null) {
            throw new Exception("No fast tag data available with fast tag ID : " + fastTagId);
        } else {
            return fastTag.getFastTagStatus();
        }
    }

    public String changeFastTagStatus(ChangeFastTagStatusRequest changeFastTagStatusRequest, String customerId) throws Exception {
        FastTag fastTag = fetchFastTagDetailsByCustomerId(customerId);
        validateChangeStatusRequest(customerId, fastTag);
        fastTag.setFastTagStatus(changeFastTagStatusRequest.getRequestedFastTagStatus());
        return "Fast Tag status changes successfully";
    }

    private void validateChangeStatusRequest(String customerId, FastTag fastTag) throws Exception {
        if (fastTag == null) {
            throw new Exception("No fast tag data available with customer ID : " + customerId);
        }
    }

    public String makeTollPayment(FastTagTollPaymentRequest fastTagTollPaymentRequest) throws Exception {
        FastTag fastTag = fetchFastTagDetailsByFastTagId(fastTagTollPaymentRequest.getFastTagId());

        if ( fastTag==null ) {
            throw new Exception("Invalid fast tag no data found for this fast tag ID");
        }
        if (fastTag.getFastTagBalance()<fastTagTollPaymentRequest.getAmount()) {
            throw new Exception("Insufficient amount in fast tag");
        }

        return "Toll deducted successfully";
    }

    public FastTag fetchFastTagDetailsByFastTagId(String fastTagId) {
        for (FastTag fastTag:fastTagData) {
            if (fastTag.getFastTagId().equalsIgnoreCase(fastTagId)) {
                return fastTag;
            }
        }
        return null;
    }

    public void updateFastTagBalanceDetailByFastTagId(String fastTagId, Double updateFastTagAmount) {
        for (FastTag fastTag:fastTagData) {
            if (fastTag.getFastTagId().equalsIgnoreCase(fastTagId)) {
                Double fastTagBalance = fastTag.getFastTagBalance();
                Double updatedFastTagBalance = fastTagBalance + updateFastTagAmount;
                fastTag.setFastTagBalance(updatedFastTagBalance);
            }
        }
    }

    public FastTag fetchFastTagDetailsByCustomerId(String customerId) {
        for (FastTag fastTag:fastTagData) {
            if (fastTag.getCustomerDetails().getCustomerId().equalsIgnoreCase(customerId)) {
                return fastTag;
            }
        }
        return null;
    }

    public void transferFastTagBalance(String senderFastTagId, String receiverFastTagId, Double amount) throws Exception {
        if (!validateFastTagBalanceTransferRequest(senderFastTagId, receiverFastTagId, amount)) {
            throw new Exception("Invalid request");
        }
        updateFastTagBalanceDetailByFastTagId(senderFastTagId, (-1)*amount);
        updateFastTagBalanceDetailByFastTagId(receiverFastTagId, amount);
    }

    private boolean validateFastTagBalanceTransferRequest(String senderFastTagId, String receiverFastTagId, Double amount) {
        return true;
    }
}

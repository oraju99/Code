package com.example.controllers;

import com.example.dto.ChangeFastTagStatusRequest;
import com.example.dto.FastTagTollPaymentRequest;
import com.example.dto.TransferFastTagBalanceRequest;
import com.example.dto.WithdrawFastTagBalanceRequest;
import com.example.enums.FastTagStatus;
import com.example.services.FastTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("/fast-tag")
public class FasttagController {

    private final FastTagService fastTagService;

    @PostMapping("/withdraw-fast-tag-balance")
    public String withdrawFastTagBalance(@RequestBody WithdrawFastTagBalanceRequest withdrawFastTagBalanceRequest)
            throws Exception {
        fastTagService.withdrawFastTagBalance(withdrawFastTagBalanceRequest);
        return "Fast tag balance withdraw successful";
    }

    // Controller to fetch the balance of the fast tag
    @GetMapping("/fetch-balance")
    public Double fetchFastTagBalance(@RequestParam String fastTagId) throws Exception {
        return fastTagService.fetchFastTagBalance(fastTagId);
    }

    // Controller to fetch the status of the fast tag - whether it is activated or deactivated
    @GetMapping("/status")
    public FastTagStatus fetchFastTagStatus(@RequestParam String fastTagId) throws Exception {
        return fastTagService.fetchFastTagStatus(fastTagId);
    }

    @PostMapping("/status")
    public String changeFastTagStatus(@RequestBody ChangeFastTagStatusRequest changeFastTagStatusRequest,
                                      @RequestHeader String customerId) throws Exception {
        return fastTagService.changeFastTagStatus(changeFastTagStatusRequest, customerId);
    }

    @PostMapping("/toll-payment")
    public String makePaymentUsingFastTag(@RequestBody FastTagTollPaymentRequest fastTagTollPaymentRequest) throws Exception {
        return fastTagService.makeTollPayment(fastTagTollPaymentRequest);
    }

    @PostMapping("/transfer-fast-tag-balance")
    public String transferFastTagBalance(@RequestBody TransferFastTagBalanceRequest transferFastTagBalanceRequest)
            throws Exception {
        fastTagService.transferFastTagBalance(transferFastTagBalanceRequest.getSenderFastTagId(),
                transferFastTagBalanceRequest.getReceiverFastTagId(),
                transferFastTagBalanceRequest.getTransferAmount());
        return "Fast tag balance withdraw successful";
    }
}

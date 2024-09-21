package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDetails {
    private String customerId;
    private String name;
    private String contactNumber;
    private String emailId;
    private String address;
    private List<BankDetails> bankDetailsList;
    private BankDetails currentLinkedBankAccount;
}

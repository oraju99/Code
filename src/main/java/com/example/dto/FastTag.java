package com.example.dto;

import com.example.enums.FastTagStatus;
import com.example.enums.FastTagVendor;
import lombok.Data;

import java.util.List;

@Data
public class FastTag {
    private String fastTagId;
    private CustomerDetails customerDetails;
    private FastTagVendor fastTagVendor;
    private Double fastTagBalance;
    private FastTagStatus fastTagStatus;
    private List<String> transactions;
}

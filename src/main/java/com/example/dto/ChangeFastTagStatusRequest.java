package com.example.dto;

import com.example.enums.FastTagStatus;
import lombok.Data;

@Data
public class ChangeFastTagStatusRequest {
    private FastTagStatus requestedFastTagStatus;
}

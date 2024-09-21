package com.example.repository;

import com.example.dto.FastTag;
import lombok.Data;

import java.util.List;

@Data
public class FastTagData {

    private List<FastTag> fastTagList;

    public FastTag fetchFastTagDetailsByFastTagId(String fastTagId) {
        for (FastTag fastTag:fastTagList) {
            if (fastTag.getFastTagId().equalsIgnoreCase(fastTagId)) {
                return fastTag;
            }
        }
        return null;
    }

    public FastTag fetchFastTagDetailsByCustomerId(String customerId) {
        for (FastTag fastTag:fastTagList) {
            if (fastTag.getCustomerDetails().getCustomerId().equalsIgnoreCase(customerId)) {
                return fastTag;
            }
        }
        return null;
    }
}

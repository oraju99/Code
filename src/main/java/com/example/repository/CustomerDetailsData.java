package com.example.repository;

import com.example.dto.CustomerDetails;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDetailsData {
    private List<CustomerDetails> customerDetailsList;

    public void addCustomerDetails(CustomerDetails customerDetails) {
        customerDetailsList.add(customerDetails);
    }

    public CustomerDetails fetchCustomerDetails(String customerId) {
        for (CustomerDetails customerDetails:customerDetailsList) {
            if (customerDetails.getCustomerId().equalsIgnoreCase(customerId)) {
                return customerDetails;
            }
        }
        return null;
    }
}

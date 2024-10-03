package com.example;

import lombok.Data;

import java.util.List;

@Data
public class Expense {
    private String paidBy;
    private double amount;
    private List<String> participants;
    private List<Double> percentageSplit;
    private SplitStrategyEnum splitStrategyEnum;
}
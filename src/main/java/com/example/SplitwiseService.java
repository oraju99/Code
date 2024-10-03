package com.example;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SplitwiseService {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Map<String, Double>> balances = new HashMap<>();

    public void addUser(String userId, String userName) {
        User user = new User(userId, userName);
        users.put(userId, user);
        balances.put(userId, new HashMap<>());
    }

    public void addExpense(Expense expense) {
        for (String moneryReceiver: expense.getParticipants()) {
            if (expense.getSplitStrategyEnum().equals(SplitStrategyEnum.EQUAL)) {
                equalSplitBalanceSheetUpdate(expense, moneryReceiver, expense.getParticipants().size());
            }
//            if (expense.getSplitStrategyEnum().equals(SplitStrategyEnum.PERCENTAGE)) {
//
//            }
        }
    }

    private void equalSplitBalanceSheetUpdate(Expense expense, String moneyReceiver, int numberOfParticipants) {
        String moneyPayer = expense.getPaidBy();
        Double splitAmount = expense.getAmount() / numberOfParticipants;
        if (!moneyReceiver.equalsIgnoreCase(expense.getPaidBy())) {
            // update the balance sheet
            for (String userId: balances.keySet()) {
                if (expense.getParticipants().contains(userId)) {
                    // this user's balance sheet needs to be updated

                    // Against the user id of paid by user updating that participant
                    updateBalanceSheetUtil(moneyPayer, moneyReceiver, (-1) * splitAmount);
                    updateBalanceSheetUtil(moneyReceiver, moneyPayer, splitAmount);
                }
            }
        }
    }

    // this method updates the balance of user B against user A
    private void updateBalanceSheetUtil(String userA, String userB, Double amount) {
        Map<String, Double> currentUserBalanceSheet = balances.get(userA);
        Double previousBalance = currentUserBalanceSheet.getOrDefault(userB, 0.0);
        Double updatedBalance = previousBalance + amount;
        currentUserBalanceSheet.put(userB, updatedBalance);
        balances.put(userA, currentUserBalanceSheet);
    }

    public void showAllExpenses() {
        for (String userA: balances.keySet()) {
            for (String userB: balances.get(userA).keySet()) {
                Double amount = balances.get(userA).get(userB);
                if (amount != 0) {
                    if (amount > 0.0) {
                        // paid by id 1
                        // amount > 0 means owes money
                        System.out.println(userA + " owes amount " + amount + " to " + userB );
                    } else {
                        // amount < 0 means gets money
                        System.out.println(userA + " gets amount " + (-1) * amount + " from " + userB );
                    }
                }
            }
        }
    }

}

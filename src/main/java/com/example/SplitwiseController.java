package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SplitwiseController {
    private final SplitwiseService splitwiseService;

    @PostMapping("/add-user")
    String addUser(String userId, String userName) {
        splitwiseService.addUser(userId, userName);
        return "User added successfully";
    }

    @PostMapping("/add-expense")
    String addExpense(@RequestBody Expense expense) {
        splitwiseService.addExpense(expense);
        return "Split added successfully";
    }

    @GetMapping("/show-expense-status")
    void showAllBalanceSheets() {
        splitwiseService.showAllExpenses();
    }
}

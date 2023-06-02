package com.example.jubileebackendeindopdracht.dto;

import com.example.jubileebackendeindopdracht.model.Transaction;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

// constructors, one empty and one with all variables
@NoArgsConstructor
@AllArgsConstructor

// getters and setters for all variable declarations
@Getter
@Setter

public class AccountDto {

    //variables declaration
    private Long id;
    private BigDecimal balance;
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;

    // apply validation rules from transaction object
    @Valid
    private List<Transaction> transactionList;

}

package com.example.jubileebackendeindopdracht.dto;

import com.example.jubileebackendeindopdracht.model.Transaction;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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

    // value must be more than zero, .
    @DecimalMin(value = "0.0", message = "Total income must be greater than zero")
    private BigDecimal totalIncome;

    @DecimalMin(value = "0.0", message = "Total expense must be greater than zero")
    private BigDecimal totalExpense;

    // apply validation rules from transaction object
    @Valid
    private List<Transaction> transactionList;


}

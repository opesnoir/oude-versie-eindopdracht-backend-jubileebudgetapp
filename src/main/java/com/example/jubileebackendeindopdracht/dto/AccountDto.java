package com.example.jubileebackendeindopdracht.dto;

import com.example.jubileebackendeindopdracht.model.Transaction;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class AccountDto {

    private Long id;
    private BigDecimal balance;
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private List<Transaction> transactionList;

    @Valid
    private Transaction transaction;
    private Long transactionId;

}

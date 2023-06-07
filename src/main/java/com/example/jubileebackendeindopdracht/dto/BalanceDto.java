package com.example.jubileebackendeindopdracht.dto;

import com.example.jubileebackendeindopdracht.model.Transaction;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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

public class BalanceDto {

    private Long id;
    @Min(value = 0)
    private BigDecimal totalIncome;
    @Min(value = 0)
    private BigDecimal totalExpense;
    private BigDecimal balance;
    private List<Transaction> transactionList;

    @Valid
    private Transaction transaction;

}

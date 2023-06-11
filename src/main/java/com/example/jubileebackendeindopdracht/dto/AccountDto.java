package com.example.jubileebackendeindopdracht.dto;

import com.example.jubileebackendeindopdracht.model.SavingGoal;
import com.example.jubileebackendeindopdracht.model.Transaction;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class AccountDto {

    private Long id;
    private LocalDate dateCreated;
    private BigDecimal balance;
    private List<Transaction> transactionList;
    private List<SavingGoal> savingGoalList;

    @Valid
    private Transaction transaction;
    private Long transactionId;

}

package com.example.jubileebackendeindopdracht.models;

import jakarta.persistence.*;
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

@Entity
@Table(name = "balances")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_income")
    private BigDecimal totalIncome;
    @Column(name = "total_expense")
    private BigDecimal totalExpense;
    private BigDecimal balance;

    @OneToMany(mappedBy = "balance")
    private List<Transaction> transactionList;

}

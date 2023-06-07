package com.example.jubileebackendeindopdracht.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

// constructors, one empty and one with all variables
@NoArgsConstructor
@AllArgsConstructor

//  getters and setters for all variable declarations
@Getter
@Setter

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;

    @Column(name = "total_income")
    private BigDecimal totalIncome;

    @Column(name = "total_expense")
    private BigDecimal totalExpense;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactionList;

}

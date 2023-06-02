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

    //variables declaration
    // Primary key (id) of the entity, automatically generated using identity column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;

    @Column(name = "total_income")
    // Specifies the name of the column in the database table that maps to the 'totalIncome' field.
    private BigDecimal totalIncome;

    @Column(name = "total_expense")
    // Specifies the name of the column in the database table that maps to the 'totalExpense' field.
    private BigDecimal totalExpense;

    // relationship(s)
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactionList;



}

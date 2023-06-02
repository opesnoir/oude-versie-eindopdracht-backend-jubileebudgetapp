package com.example.jubileebackendeindopdracht.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


// constructors, one empty and one with all variables
@NoArgsConstructor
@AllArgsConstructor

// getters and setters for all variable declarations
@Getter
@Setter

@Entity
@Table(name = "transactions")
public class Transaction {

    //variables declaration
    // Primary key (id) of the entity, automatically generated using identity column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //TODO: tijd over: enumeration type maken en daar dan de optie income en expense inplaatsen
    private String income;
    private String expense;
    private BigDecimal amount;
    private LocalDate date;
    private String category;
    private String payee;

    @Column(name = "payment_method")
    // Specifies the name of the column in the database table that maps to the 'paymentMethod' field.
    private String paymentMethod;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}

package com.example.jubileebackendeindopdracht.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


// Constructors, one empty and one with all variables
@NoArgsConstructor
@AllArgsConstructor


// Getters and setters for all variable declarations
@Getter
@Setter

@Entity
@Table(name = "transactions")
public class Transaction {

    // Primary key (id) of the entity, automatically generated
    @Id
    @GeneratedValue
    private Long id;
    private String income;
    private String expense;
    private BigDecimal amount;
    private LocalDate date;
    private String categorie;
    private String payee;
    private String paymentMethod;

}

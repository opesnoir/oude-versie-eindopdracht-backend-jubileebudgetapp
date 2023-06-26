package com.example.jubileebackendeindopdracht.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactionList;

    @OneToMany(mappedBy = "account")
    private List<SavingGoal> savingGoalList;

    @OneToMany(mappedBy = "account")
    private List<Upload> upload;

    @OneToOne
    @JsonIgnore
    private User user;

}

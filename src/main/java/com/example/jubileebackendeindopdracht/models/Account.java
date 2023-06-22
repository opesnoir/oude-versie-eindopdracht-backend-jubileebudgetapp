package com.example.jubileebackendeindopdracht.models;

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

    @Column(name = "date_created")
    private LocalDate dateCreated;
    private BigDecimal balance;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactionList;

    @OneToMany(mappedBy = "account")
    private List<Upload> upload;

    @OneToMany(mappedBy = "account")
    private List<SavingGoal> savingGoalList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}

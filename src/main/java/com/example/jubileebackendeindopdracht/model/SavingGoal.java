package com.example.jubileebackendeindopdracht.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "savings_goals")
public class SavingGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "start_amount")
    private BigDecimal startAmount;
    @Column(name = "target_amount")
    private BigDecimal targetAmount;
    @Column(name = "current_amount")
    private BigDecimal currentAmount;
    @Column(name = "amount-added")
    private BigDecimal amountAdded;
    @Column(name = "amount_subtracted")
    private BigDecimal amountSubtracted;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}

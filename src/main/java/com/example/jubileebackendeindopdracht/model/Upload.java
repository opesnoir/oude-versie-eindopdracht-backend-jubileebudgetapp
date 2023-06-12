package com.example.jubileebackendeindopdracht.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "contracts")
public class Upload {

    @Id
    @GeneratedValue
    private Long id;
    private String payee;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}

package com.example.jubileebackendeindopdracht.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor

@Setter
@Getter

@Entity
@Table (name = "voorbeeldincomes")
public class VoorbeeldIncome {

    @Id
    @GeneratedValue
    private Long id;
    public int money;
    public String title;





}

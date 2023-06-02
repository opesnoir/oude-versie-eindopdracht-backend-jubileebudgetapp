package com.example.jubileebackendeindopdracht.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


}

package com.example.jubileebackendeindopdracht.models;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.List;


//TODO: kiezen of je Profile wil uitwerken, dan moet je hem nog aanmaken als entiteit, dto etc.

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private Date profileCreationDate;

    @OneToMany
    @JoinColumn(name = "upload_id")
    private List<Upload> uploads;


}

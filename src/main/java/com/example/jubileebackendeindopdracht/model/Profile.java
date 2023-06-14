package com.example.jubileebackendeindopdracht.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


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

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Lob
    @Column(name = "profile_image")
    private byte[] profileImage;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

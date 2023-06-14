package com.example.jubileebackendeindopdracht.model;

import jakarta.persistence.*;
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

    @Lob
    private byte[] profileUpload;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "upload_id")
    private Upload upload;

}

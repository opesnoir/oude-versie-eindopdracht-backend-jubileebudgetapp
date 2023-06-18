package com.example.jubileebackendeindopdracht.dtos;

import com.example.jubileebackendeindopdracht.models.Authority;
import com.example.jubileebackendeindopdracht.models.Upload;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class ProfileDto {

    private Long id;
    private String username;
    private String email;
    private Date profileCreationDate;

    public Set<Authority> authorities;
    private Long accountId;

    @Valid
    private Upload upload;
    private Long uploadId;

}

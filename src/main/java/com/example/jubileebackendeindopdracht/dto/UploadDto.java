package com.example.jubileebackendeindopdracht.dto;

import com.example.jubileebackendeindopdracht.model.Account;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class UploadDto {

    private Long id;

    @NotBlank(message = "Payee should not be blank. Please provide the name of the payee." )
    private String payee;

    @Valid
    private Account account;
    private Long accountId;

}

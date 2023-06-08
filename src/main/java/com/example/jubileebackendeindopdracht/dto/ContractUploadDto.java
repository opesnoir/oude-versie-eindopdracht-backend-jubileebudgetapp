package com.example.jubileebackendeindopdracht.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class ContractUploadDto {

    private Long id;

    @NotBlank(message = "Payee should not be blank. Please provide the name of the payee." )
    private String payee;

    private String contractPurpose;
    private String contractPdfUrl;

}

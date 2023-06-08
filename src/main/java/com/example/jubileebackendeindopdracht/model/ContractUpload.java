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
public class ContractUpload {

    @Id
    @GeneratedValue
    private Long id;
    private String payee;

    @Column(name = "contract_purpose")
    private String contractPurpose;

    @Column(name = "contract_pdf_url")
    private String contractPdfUrl;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}

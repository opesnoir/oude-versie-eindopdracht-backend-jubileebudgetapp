package com.example.jubileebackendeindopdracht.dtos;

import com.example.jubileebackendeindopdracht.models.Account;
import com.example.jubileebackendeindopdracht.models.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class TransactionDto {

    private Long id;

    @NotNull(message = "Transaction type cannot be null")
    @Pattern(regexp = "^(?i)(income|expense)$", message = "Please enter 'INCOME' or 'EXPENSE'")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @NotBlank(message = "Amount is required")
    @DecimalMin(value = "0.00", message = "Amount must be greater than or equal to 0.00")
    private BigDecimal amount;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Categorie is required")
    private String category;

    @NotBlank(message = "Payee is required")
    private String payee;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    @Valid
    private Account account;
    private Long accountId;

}

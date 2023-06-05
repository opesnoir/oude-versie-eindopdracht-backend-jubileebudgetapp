package com.example.jubileebackendeindopdracht.dto;

import com.example.jubileebackendeindopdracht.model.Account;
import com.example.jubileebackendeindopdracht.model.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

// constructors, one empty and one with all variables
@NoArgsConstructor
@AllArgsConstructor

// getters and setters for all variable declarations
@Getter
@Setter

public class TransactionDto {

    //variables declaration
    private Long id;

    //enumeration type option income or expense
    @NotNull(message = "Transaction type cannot be null")
    @Pattern(regexp = "^(?i)(income|expense)$", message = "Please enter 'income' or 'expense'")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    //@AssertTrue boolean checks if income or expense is provided
    @AssertTrue(message = "Either income or expense should be provided")
    private boolean isIncomeOrExpenseProvided(){
        return type == TransactionType.INCOME || type == TransactionType.EXPENSE;
    }

    //@NotBlank checks if the value is not empty and does not consist of spaces only (not null gave problems when trying to use BigDecimal.ZERO in the code for the account)
    @NotBlank(message = "Amount is required")
    @DecimalMin(value = "0.00", message = "Amount must be greater than or equal to 0.00")
    private BigDecimal amount;

    @NotNull(message = "Date is required")
    private LocalDate date;

    //@NotBlank checks if not empty
    @NotBlank(message = "Categorie is required")
    private String category;

    @NotBlank(message = "Payee is required")
    private String payee;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    // Relationship(s)
    private Account account;

}

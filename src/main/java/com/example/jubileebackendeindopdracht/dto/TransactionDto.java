package com.example.jubileebackendeindopdracht.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String income;
    private String expense;
    //@AssertTrue boolean checks if income or expense is provided
    @AssertTrue(message = "Either income or expense should be provided")
    private boolean isIncomeOrExpenseProvided(){
        return income != null || expense != null;
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

    // Relationships

}

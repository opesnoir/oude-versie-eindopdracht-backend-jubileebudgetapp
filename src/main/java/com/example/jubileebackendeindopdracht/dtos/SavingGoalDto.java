package com.example.jubileebackendeindopdracht.dtos;

import com.example.jubileebackendeindopdracht.models.Account;
import com.example.jubileebackendeindopdracht.models.SavingGoalOperation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class SavingGoalDto {

    private Long id;

    @NotNull(message = "Saving goal name cannot be null")
    private String name;
    private BigDecimal startAmount;
    @NotNull(message = "Saving goal amount cannot be null")
    private BigDecimal targetAmount;
    private BigDecimal currentAmount;

    @NotNull(message = "Saving goal operation cannot be null")
    @Pattern(regexp = "^(?i)(added|subtracted)$", message = "Please enter 'ADDED' or 'SUBTRACTED'")
    @Enumerated(EnumType.STRING)
    private SavingGoalOperation savingGoalOperation;

    //TODO: deze twee verwijderen, enum neemt het over private BigDecimal amountAdded;
    //    private BigDecimal amountSubtracted;
    private BigDecimal amountAdded;
    private BigDecimal amountSubtracted;


    @Valid
    private Account account;
    private Long accountId;

}

package com.example.jubileebackendeindopdracht.dtos;

import com.example.jubileebackendeindopdracht.models.Account;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
    private BigDecimal amountAdded;
    private BigDecimal amountSubtracted;

    @Valid
    private Account account;
    private Long accountId;

}

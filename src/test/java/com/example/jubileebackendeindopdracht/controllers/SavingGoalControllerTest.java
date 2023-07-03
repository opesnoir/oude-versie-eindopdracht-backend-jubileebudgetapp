package com.example.jubileebackendeindopdracht.controllers;

import com.example.jubileebackendeindopdracht.dtos.SavingGoalDto;
import com.example.jubileebackendeindopdracht.models.Account;
import com.example.jubileebackendeindopdracht.models.SavingGoal;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.SavingGoalRepository;
import com.example.jubileebackendeindopdracht.services.SavingGoalService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class SavingGoalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
     SavingGoalService savingGoalService;

    @Autowired
     SavingGoalRepository savingGoalRepository;

    @Autowired
     AccountRepository accountRepository;

    SavingGoal savingGoal1;
    SavingGoal savingGoal2;
    SavingGoalDto savingGoalDto1;
    SavingGoalDto savingGoalDto2;

    Account account1;


    @BeforeEach
    void setUp() {

        savingGoalRepository.deleteAll();
        accountRepository.deleteAll();

        account1 = new Account();
        accountRepository.save(account1);

        savingGoal1 = new SavingGoal(1L, "Doel 1", BigDecimal.ZERO, BigDecimal.valueOf(1000), BigDecimal.ZERO, account1);
        savingGoal2 = new SavingGoal(2L, "Doel 2", BigDecimal.ZERO, BigDecimal.valueOf(2000), BigDecimal.ZERO, account1);

        savingGoal1 = savingGoalRepository.save(savingGoal1);
        savingGoal2 = savingGoalRepository.save(savingGoal2);

        savingGoalDto1 = new SavingGoalDto(
                savingGoal1.getId(),
                "Doel 1",
                BigDecimal.ZERO,
                BigDecimal.valueOf(1000),
                BigDecimal.ZERO,
                account1,
                savingGoal1.getId()
        );

        savingGoalDto2 = new SavingGoalDto(
                savingGoal2.getId(),
                "Doel 2",
                BigDecimal.ZERO,
                BigDecimal.valueOf(2000),
                BigDecimal.ZERO,
                account1,
                savingGoal2.getId()
        );

    }

    @Test
    void getSavingGoal() throws Exception {
        Long id = savingGoal1.getId();

        mockMvc.perform(get("/saving_goals/{id}", id))
               .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Doel 1"))
                .andExpect(jsonPath("startAmount").value(0))
                .andExpect(jsonPath("targetAmount").value(1000));

    }

    @Test
    void getAllSavingGoals() throws Exception {
        SavingGoalDto savingGoalDto1 = new SavingGoalDto();
        savingGoalDto1.setName("Doel 1");
        savingGoalDto1.setStartAmount(BigDecimal.ZERO);
        savingGoalDto1.setTargetAmount(BigDecimal.valueOf(1000));

        SavingGoalDto savingGoalDto2 = new SavingGoalDto();
        savingGoalDto2.setName("Doel 2");
        savingGoalDto2.setStartAmount(BigDecimal.ZERO);
        savingGoalDto2.setTargetAmount(BigDecimal.valueOf(2000));

        mockMvc.perform(get("/saving_goals"))

                .andExpect(status().isOk())

                .andExpect( jsonPath("$[0].name").value("Doel 1"))
                .andExpect( jsonPath("$[0].startAmount").value(0))
                .andExpect( jsonPath("$[0].targetAmount").value(1000))
                .andExpect( jsonPath("$[1].name").value("Doel 2"))
                .andExpect( jsonPath("$[1].startAmount").value(0))
                .andExpect( jsonPath("$[1].targetAmount").value(2000));
    }

    @Test
    void deleteSavingGoal() throws Exception {
        Long id = savingGoal1.getId();

        mockMvc.perform(delete("/saving_goals/{id}", id))
                .andExpect(status().isNoContent());
    }

}
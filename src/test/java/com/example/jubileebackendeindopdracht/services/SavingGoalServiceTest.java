package com.example.jubileebackendeindopdracht.services;

import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.SavingGoalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SavingGoalServiceTest {

    @Mock
    SavingGoalRepository savingGoalRepository;

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    SavingGoalService savingGoalService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled
    void getAllSavingGoals() {
    }

    @Test
    @Disabled
    void getSavingGoalById() {
    }

    @Test
    @Disabled
    void createSavingGoal() {
    }

    @Test
    @Disabled
    void updateSavingGoal() {
    }

    @Test
    @Disabled
    void deleteSavingGoal() {
    }


}
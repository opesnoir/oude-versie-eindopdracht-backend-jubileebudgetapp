package com.example.jubileebackendeindopdracht.services;

import com.example.jubileebackendeindopdracht.models.SavingGoal;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.SavingGoalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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

    @Captor
    ArgumentCaptor<SavingGoal> captor;

    SavingGoal savingGoal1;
    SavingGoal savingGoal2;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled
    void getAllSavingGoals() {
        //arrange
        //act
        //assert
    }

    @Test
    @Disabled
    void getSavingGoalById() {
        //arrange
        //act
        //assert
    }

    @Test
    @Disabled
    void createSavingGoal() {
        //arrange
        //act
        //assert
    }

    @Test
    @Disabled
    void updateSavingGoal() {
        //arrange
        //act
        //assert
    }

    @Test
    @Disabled
    void deleteSavingGoal() {
        //arrange
        //act
        //assert
    }


}
package com.example.jubileebackendeindopdracht.services;

import com.example.jubileebackendeindopdracht.dtos.SavingGoalDto;
import com.example.jubileebackendeindopdracht.models.Account;
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

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

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

    Account account1;

    @BeforeEach
    void setUp() {
        //assert
        account1 = new Account();

        savingGoal1 = new SavingGoal();
        savingGoal1.setId(1L);
        savingGoal1.setName("holiday");
        savingGoal1.setStartAmount(BigDecimal.valueOf(500));
        savingGoal1.setTargetAmount(BigDecimal.valueOf(1750));
        savingGoal1.setCurrentAmount(BigDecimal.valueOf(1200));
        savingGoal1.setAccount(account1);

        savingGoal2 = new SavingGoal();
        savingGoal2.setId(2L);
        savingGoal2.setName("buffer");
        savingGoal2.setStartAmount(BigDecimal.valueOf(100));
        savingGoal2.setTargetAmount(BigDecimal.valueOf(10000));
        savingGoal2.setCurrentAmount(BigDecimal.valueOf(2000));
        savingGoal2.setAccount(account1);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllSavingGoals() {
        //act
        when(savingGoalRepository.findAll()).thenReturn(List.of(savingGoal1, savingGoal2));

        List<SavingGoal> savingGoalList = savingGoalRepository.findAll();
        List<SavingGoalDto> savingGoalDtos = savingGoalService.getAllSavingGoals();

        //assert
        assertEquals(savingGoalList.get(0).getName(), savingGoalDtos.get(0).getName());
        assertEquals(savingGoalList.get(0).getStartAmount(), savingGoalDtos.get(0).getStartAmount());
        assertEquals(savingGoalList.get(0).getCurrentAmount(), savingGoalDtos.get(0).getCurrentAmount());
        assertEquals(savingGoalList.get(0).getTargetAmount(), savingGoalDtos.get(0).getTargetAmount());
        assertEquals(savingGoalList.get(0).getAccount(), savingGoalDtos.get(0).getAccount());

    }

    @Test
    void getAllSavingGoals_emptyList() {
        // Arrange
        when(savingGoalRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<SavingGoalDto> savingGoalDtos = savingGoalService.getAllSavingGoals();

        // Assert
        assertTrue(savingGoalDtos.isEmpty());
    }


    @Test
    @Disabled
    void getSavingGoalById() {
        //act
        Long savingGoalId = 2L;
        when(savingGoalRepository.findById(savingGoalId)).thenReturn(Optional.of(savingGoal2));

        SavingGoal savingGoal = savingGoalRepository.findById(savingGoalId).get();
        SavingGoalDto savingGoalDto = savingGoalService.getSavingGoalById(savingGoalId);

        //assert
        assertEquals(savingGoal.getName(), savingGoalDto.getName());
        assertEquals(savingGoal.getStartAmount(), savingGoalDto.getStartAmount());
        assertEquals(savingGoal.getCurrentAmount(), savingGoalDto.getCurrentAmount());
        assertEquals(savingGoal.getTargetAmount(), savingGoalDto.getTargetAmount());
        assertEquals(savingGoal.getAccount(), savingGoalDto.getAccount());
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
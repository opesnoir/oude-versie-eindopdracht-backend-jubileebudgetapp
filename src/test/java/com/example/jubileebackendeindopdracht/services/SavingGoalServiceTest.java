package com.example.jubileebackendeindopdracht.services;

import com.example.jubileebackendeindopdracht.dtos.SavingGoalDto;
import com.example.jubileebackendeindopdracht.exceptions.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.models.Account;
import com.example.jubileebackendeindopdracht.models.SavingGoal;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.SavingGoalRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
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

    }


    @Test
    void updateSavingGoal() {
        //arrange
        Long id = 1L;
        SavingGoal existingSavingGoal = new SavingGoal();
        when(savingGoalRepository.findById(id)).thenReturn(Optional.of(existingSavingGoal));

        SavingGoalDto updatedSavingGoalDto = new SavingGoalDto();
        assertEquals(existingSavingGoal.getName(), updatedSavingGoalDto.getName());
        assertEquals(existingSavingGoal.getStartAmount(), updatedSavingGoalDto.getStartAmount());
        assertEquals(existingSavingGoal.getCurrentAmount(), updatedSavingGoalDto.getCurrentAmount());
        assertEquals(existingSavingGoal.getTargetAmount(), updatedSavingGoalDto.getTargetAmount());
        assertEquals(existingSavingGoal.getAccount(), updatedSavingGoalDto.getAccount());

        SavingGoal savedSavingGoal = new SavingGoal();
        when(savingGoalRepository.save(existingSavingGoal)).thenReturn(savedSavingGoal);

        //act
        SavingGoalDto result = savingGoalService.updateSavingGoal(id, updatedSavingGoalDto);

        //assert
        assertNotNull(result);

        verify(savingGoalRepository).save(existingSavingGoal);

    }

    @Test
    void updateSavingGoal_RecordNotFound(){
        //arrange
        Long savingGoalId = 1L;
        when(savingGoalRepository.findById(savingGoalId)).thenReturn(Optional.empty());
        SavingGoalDto updatedSavingGoalDto = new SavingGoalDto();

        //act, assert
        assertThrows(UserIdNotFoundException.class, () -> savingGoalService.updateSavingGoal(savingGoalId, updatedSavingGoalDto));
    }


    @Test
    void deleteSavingGoal() {
        //arrange
        Long id = 1L;
        SavingGoal savingGoal = new SavingGoal();
        when(savingGoalRepository.findById(id)).thenReturn(Optional.of(savingGoal));

        //act
        SavingGoalDto savingGoalDto = savingGoalService.deleteSavingGoal(1L);

        //assert
        assertNotNull(savingGoalDto);
        assertEquals(savingGoal.getId(), savingGoalDto.getId());
        assertEquals(savingGoal.getName(), savingGoalDto.getName());
        assertEquals(savingGoal.getStartAmount(), savingGoalDto.getStartAmount());
        assertEquals(savingGoal.getCurrentAmount(), savingGoalDto.getCurrentAmount());
        assertEquals(savingGoal.getTargetAmount(), savingGoalDto.getTargetAmount());
        assertEquals(savingGoal.getAccount(), savingGoalDto.getAccount());

        verify(savingGoalRepository).delete(savingGoal);

    }


}
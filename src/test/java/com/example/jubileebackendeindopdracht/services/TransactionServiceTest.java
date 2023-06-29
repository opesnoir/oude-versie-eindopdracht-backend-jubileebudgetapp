package com.example.jubileebackendeindopdracht.services;

import com.example.jubileebackendeindopdracht.models.Transaction;
import com.example.jubileebackendeindopdracht.models.TransactionType;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.TransactionRepository;
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
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    TransactionService transactionService;

    @Captor
    ArgumentCaptor<Transaction> captor;

    Transaction transaction1;
    Transaction transaction2;
    Transaction transaction3;

    @BeforeEach
    void setUp() {
        transaction1 = new Transaction(1L, "Ekoplaza", LocalDate.parse("2023-10-12"), "Grocery", "cash", TransactionType.EXPENSE, new BigDecimal("3.50"), account);
        transaction2 = new Transaction(1L, "Bijenkorf", LocalDate.parse("2023-11-25"), "Home goods", "debit card", TransactionType.EXPENSE, new BigDecimal("25.00"), account);
        transaction3 = new Transaction(1L, "Saving", LocalDate.parse("2023-1-15"), "Vacation", "saving card", TransactionType.INCOME, new BigDecimal("750.00"), account);
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled
    void getAllTransactions() {
        //arrange
        //act
        //assert
    }

    @Test
    @Disabled
    void getTransaction() {
        //arrange
        //act
        //assert
    }

    @Test
    @Disabled
    void createTransaction() {
        //arrange
        //act
        //assert
    }

    @Test
    @Disabled
    void updateTransaction() {
        //arrange
        //act
        //assert
    }

    @Test
    @Disabled
    void deleteTransaction() {
        //arrange
        //act
        //assert
    }

    @Test
    @Disabled
    void calculateTotalIncome() {
        //arrange
        //act
        //assert
    }

    @Test
    @Disabled
    void calculateTotalExpense() {
        //arrange
        //act
        //assert
    }

    @Test
    @Disabled
    void calculateBalance() {
        //arrange
        //act
        //assert
    }

}
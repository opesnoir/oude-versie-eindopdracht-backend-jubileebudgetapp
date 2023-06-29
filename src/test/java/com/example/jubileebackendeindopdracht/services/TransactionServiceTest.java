package com.example.jubileebackendeindopdracht.services;

import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.TransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    TransactionService transactionService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled
    void getAllTransactions() {
    }

    @Test
    @Disabled
    void getTransaction() {
    }

    @Test
    @Disabled
    void createTransaction() {
    }

    @Test
    @Disabled
    void updateTransaction() {
    }

    @Test
    @Disabled
    void deleteTransaction() {
    }

    @Test
    @Disabled
    void calculateTotalIncome() {
    }

    @Test
    @Disabled
    void calculateTotalExpense() {
    }

    @Test
    @Disabled
    void calculateBalance() {
    }

}
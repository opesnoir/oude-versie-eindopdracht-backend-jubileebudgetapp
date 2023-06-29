package com.example.jubileebackendeindopdracht.services;

import com.example.jubileebackendeindopdracht.dtos.TransactionDto;
import com.example.jubileebackendeindopdracht.models.Account;
import com.example.jubileebackendeindopdracht.models.Transaction;
import com.example.jubileebackendeindopdracht.models.TransactionType;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.TransactionRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

    Account account1;



    @BeforeEach
    void setUp() {
        //assert
        account1 = new Account();

        transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setType(TransactionType.INCOME);
        transaction1.setCategory("Grocery");
        transaction1.setAmount(BigDecimal.valueOf(3.50));
        transaction1.setDate(LocalDate.ofEpochDay(2023-1-2));
        transaction1.setPaymentMethod("debit-card");
        transaction1.setAccount(account1);

        transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setType(TransactionType.EXPENSE);
        transaction2.setCategory("Grocery");
        transaction2.setAmount(BigDecimal.valueOf(13.50));
        transaction2.setDate(LocalDate.ofEpochDay(2023-4-2));
        transaction2.setPaymentMethod("cash");
        transaction2.setAccount(account1);

    }

    @Test
    void getAllTransactions() {
        //act
        when(transactionRepository.findAll()).thenReturn(List.of(transaction1, transaction2));

        List<Transaction> transactionList = transactionRepository.findAll();
        List<TransactionDto> transactionDtos = transactionService.getAllTransactions();

        //assert
        assertEquals(transactionList.get(0).getPayee(), transactionDtos.get(0).getPayee());
        assertEquals(transactionList.get(0).getDate(), transactionDtos.get(0).getDate());
        assertEquals(transactionList.get(0).getCategory(), transactionDtos.get(0).getCategory());
        assertEquals(transactionList.get(0).getPaymentMethod(), transactionDtos.get(0).getPaymentMethod());
        assertEquals(transactionList.get(0).getType(), transactionDtos.get(0).getType());
        assertEquals(transactionList.get(0).getDate(), transactionDtos.get(0).getDate());
        assertEquals(transactionList.get(0).getAmount(), transactionDtos.get(0).getAmount());

    }

    @Test
    @Disabled
    void getTransaction() {
        //act
        Long id = 2L;
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction2));

        Transaction transaction = transactionRepository.findById(id).get();
        TransactionDto transactionDto = transactionService.getTransaction(id);

        //assert
        assertEquals(transaction.getPayee(), transactionDto.getPayee());
        assertEquals(transaction.getDate(), transactionDto.getDate());
        assertEquals(transaction.getCategory(), transactionDto.getCategory());
        assertEquals(transaction.getPaymentMethod(), transactionDto.getPaymentMethod());
        assertEquals(transaction.getType(), transactionDto.getType());
        assertEquals(transaction.getDate(), transactionDto.getDate());
        assertEquals(transaction.getAmount(), transactionDto.getAmount());

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
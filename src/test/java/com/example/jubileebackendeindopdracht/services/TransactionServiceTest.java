package com.example.jubileebackendeindopdracht.services;

import com.example.jubileebackendeindopdracht.dtos.TransactionDto;
import com.example.jubileebackendeindopdracht.exceptions.TransactionNotFoundException;
import com.example.jubileebackendeindopdracht.exceptions.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.models.Account;
import com.example.jubileebackendeindopdracht.models.Transaction;
import com.example.jubileebackendeindopdracht.models.TransactionType;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
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

import static com.example.jubileebackendeindopdracht.models.TransactionType.EXPENSE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
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

    TransactionDto transactionDto;



    @BeforeEach
    void setUp() {
        //assert
        account1 = new Account();
        account1.setId(1L);
        accountRepository.save(account1);

        transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setType(TransactionType.INCOME);
        transaction1.setCategory("Grocery return");
        transaction1.setAmount(BigDecimal.valueOf(3.50));
        transaction1.setDate(LocalDate.ofEpochDay(2023-1-2));
        transaction1.setPaymentMethod("debit-card");
        transaction1.setAccount(account1);

        transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setType(EXPENSE);
        transaction2.setCategory("Grocery");
        transaction2.setAmount(BigDecimal.valueOf(13.50));
        transaction2.setDate(LocalDate.ofEpochDay(2023-4-2));
        transaction2.setPaymentMethod("cash");
        transaction2.setAccount(account1);

        transactionDto = new TransactionDto();
        transactionDto.setPayee("Expected Payee");
        transactionDto.setDate(LocalDate.of(2023, 1, 1));
        transactionDto.setCategory("Expected Category");
        transactionDto.setPaymentMethod("Expected Payment Method");
        transactionDto.setType(EXPENSE);
        transactionDto.setAmount(BigDecimal.valueOf(100));
        transactionDto.setAccountId(1L);


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
    void createTransaction() {
        // arrange (wat de methode die ik test nodig heeft)

        when(accountRepository.findById(account1.getId())).thenReturn(Optional.of(account1));


        // act (ik roep de methode daadwerkelijk aan)
        TransactionDto createdTransactionDto = transactionService.createTransaction(transactionDto, account1.getId());

        // assert
        assertEquals(transactionDto.getPayee(), createdTransactionDto.getPayee());
        assertEquals(transactionDto.getDate(), createdTransactionDto.getDate());
        assertEquals(transactionDto.getCategory(), createdTransactionDto.getCategory());
        assertEquals(transactionDto.getPaymentMethod(), createdTransactionDto.getPaymentMethod());
        assertEquals(transactionDto.getType(), createdTransactionDto.getType());
        assertEquals(transactionDto.getAmount(), createdTransactionDto.getAmount());

        verify(transactionRepository).save(captor.capture());
    }



    @Test
    void createTransaction_UserIdNotFound() {
        //arrange
        Long accountId = 1L;
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());
        TransactionDto transactionDto = new TransactionDto();

        //act, assert
        assertThrows(UserIdNotFoundException.class, () -> transactionService.createTransaction(transactionDto, accountId));
    }

    @Test
    void updateTransaction() {
        //arrange
        Long id = 1L;
        Transaction existingTransaction = new Transaction();
        when(transactionRepository.findById(id)).thenReturn(Optional.of(existingTransaction));

        TransactionDto updatedTransactionDto = new TransactionDto();
        assertEquals(existingTransaction.getPayee(), updatedTransactionDto.getPayee());
        assertEquals(existingTransaction.getDate(), updatedTransactionDto.getDate());
        assertEquals(existingTransaction.getCategory(), updatedTransactionDto.getCategory());
        assertEquals(existingTransaction.getPaymentMethod(), updatedTransactionDto.getPaymentMethod());
        assertEquals(existingTransaction.getType(), updatedTransactionDto.getType());
        assertEquals(existingTransaction.getDate(), updatedTransactionDto.getDate());
        assertEquals(existingTransaction.getAmount(), updatedTransactionDto.getAmount());

        Transaction savedTransaction = new Transaction();
        when(transactionRepository.save(existingTransaction)).thenReturn(savedTransaction);

        //act
        TransactionDto result = transactionService.updateTransaction(id, updatedTransactionDto);

        //assert
        assertNotNull(result);

        verify(transactionRepository).save(existingTransaction);


    }

    @Test
    void updateTransaction_TransactionNotFound() {
        //arrange
        Long id = 1L;
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());
        TransactionDto updatedTransactionDto = new TransactionDto();

        //act, assert
        assertThrows(TransactionNotFoundException.class, () -> transactionService.updateTransaction(id, updatedTransactionDto));
    }


    @Test
    void deleteTransaction() {
        //arrange
        Long id = 1L;
        Transaction transaction = new Transaction();
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));

        //act
        TransactionDto transactionDto = transactionService.deleteTransaction(1L);
        //assert
        assertNotNull(transactionDto);
        assertEquals(transaction.getId(), transactionDto.getId());
        assertEquals(transaction.getType(), transactionDto.getType());
        assertEquals(transaction.getAmount(), transactionDto.getAmount());
        assertEquals(transaction.getDate(), transactionDto.getDate());
        assertEquals(transaction.getCategory(), transactionDto.getCategory());
        assertEquals(transaction.getPayee(), transactionDto.getPayee());
        assertEquals(transaction.getPaymentMethod(), transactionDto.getPaymentMethod());

        verify(transactionRepository).delete(transaction);
    }

    @Test
    void calculateTotalIncome() {
        //arrange
        BigDecimal expectedTotalIncome = new BigDecimal("1000.00");
        when(transactionRepository.calculateTotalIncome()).thenReturn(expectedTotalIncome);

        //act
        BigDecimal actualTotalIncome = transactionService.calculateTotalIncome();

        //assert
        assertEquals(expectedTotalIncome, actualTotalIncome);
    }

    @Test
    void calculateTotalExpense() {
        //arrange
        BigDecimal expectedTotalExpense = new BigDecimal("50.00");
        when(transactionRepository.calculateTotalExpense()).thenReturn(expectedTotalExpense);

        //act
        BigDecimal actualTotalExpense = transactionService.calculateTotalExpense();

        //assert
        assertEquals(expectedTotalExpense, actualTotalExpense);
    }

    @Test
    void calculateBalance() {
        // Arrange
        BigDecimal totalIncome = new BigDecimal("1000.00");
        BigDecimal totalExpense = new BigDecimal("50.00");
        when(transactionRepository.calculateTotalIncome()).thenReturn(totalIncome);
        when(transactionRepository.calculateTotalExpense()).thenReturn(totalExpense);

        // Act
        BigDecimal actualBalance = transactionService.calculateBalance();

        // Assert
        BigDecimal expectedBalance = totalIncome.subtract(totalExpense);
        assertEquals(expectedBalance, actualBalance);
    }

}
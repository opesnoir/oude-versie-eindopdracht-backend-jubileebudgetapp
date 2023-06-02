package com.example.jubileebackendeindopdracht.repository;

import com.example.jubileebackendeindopdracht.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository <Transaction, Long>{

    //query's
/*    List<Transaction> findAllByDate(LocalDate date);
    List<Transaction> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Transaction> findAllByDateIsAfter(LocalDate date);
    List<Transaction> findAllByDateIsBefore(LocalDate date);

    List<Transaction> findAllByAmount(BigDecimal amount);*/
    List<Transaction> findAllByCategoryIgnoreCase(String category);
/*
    List<Transaction> findAllByPayeeIgnoreCase(String payee);
    List<Transaction> findAllByPaymentMethod(String paymentMethode);

    List<Transaction> findAllByIncome(String income);
    List<Transaction> findAllByExpense(String expense);
*/

}

package com.example.jubileebackendeindopdracht.service;


import com.example.jubileebackendeindopdracht.dto.TransactionDto;
import com.example.jubileebackendeindopdracht.model.Transaction;
import com.example.jubileebackendeindopdracht.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {

    // repository for accessing transaction data
    private final TransactionRepository transactionRepository;

    // constructor for initializing TransactionService with the corresponding repository
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // methode to get all transactions from the repository
    public List<TransactionDto> getAllTransactions(){

        List<TransactionDto> transactionDtos = new ArrayList<>();
        List<Transaction> transactions = transactionRepository.findAll();

        for (Transaction transaction : transactions){
            TransactionDto transactionDto = transferToDto(transaction);
            transactionDtos.add(transactionDto);
        }
        return transactionDtos;
    }

    // methode to get one single transaction



    // helper method to convert a Transaction object to a TransactionDto object
    private TransactionDto transferToDto(Transaction transaction){

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setIncome(transaction.getIncome());
        transactionDto.setExpense(transaction.getExpense());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setCategory(transaction.getCategory());
        transactionDto.setPayee(transaction.getPayee());
        transactionDto.setPaymentMethod(transaction.getPaymentMethod());

        return transactionDto;
    }

    //TODO: vragen waarom hij zegt dat de code duplicaat is,terwijl het andere code is.

    // Helper method to convert a TransactionDto object to a Transaction object
/*    public Transaction transferToTransaction(TransactionDto transactionDto){

        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setIncome(transactionDto.getIncome());
        transaction.setExpense(transactionDto.getExpense());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDate(transactionDto.getDate());
        transaction.setCategory(transactionDto.getCategory());
        transaction.setPayee(transactionDto.getPayee());
        transaction.setPaymentMethod(transactionDto.getPaymentMethod());

        return transaction;

    }*/


}

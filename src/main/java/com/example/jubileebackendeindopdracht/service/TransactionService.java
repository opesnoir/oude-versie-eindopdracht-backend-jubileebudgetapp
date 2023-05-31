package com.example.jubileebackendeindopdracht.service;


import com.example.jubileebackendeindopdracht.dto.TransactionDto;
import com.example.jubileebackendeindopdracht.exception.TransactionNotFoundException;
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
        // make a list to hold all the transaction dto objects
        List<TransactionDto> transactionDtos = new ArrayList<>();
        // get all transactions from the repository
        List<Transaction> transactions = transactionRepository.findAll();
        // loop over each transaction
        for (Transaction transaction : transactions){
            // convert the Transaction object to a transaction dto object, see helpen method transferToDto
            TransactionDto transactionDto = transferToDto(transaction);
            // add the transaction dto object to the list
            transactionDtos.add(transactionDto);
        }
        // return the list of transaction dto objects
        return transactionDtos;
    }

    // methode to get one single transaction
    public TransactionDto getTransaction(Long id){
        // retrieve the transaction from the repository by id
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        // check if the transaction is present
        if (transactionOptional.isPresent()){
            Transaction transaction = transactionOptional.get();
            // convert the transaction object to a transactionDto object
            return transferToDto(transaction);
        } else { // transaction not found: throw a transaction not found exception
            throw new TransactionNotFoundException(id);
        }
    }

    // methode to create a single transaction
    public TransactionDto createTransaction(TransactionDto transactionDto){
        // convert transaction dto to transaction, met helper methode transfer to transaction
        Transaction transaction = transferToTransaction(transactionDto);
        // save the transaction in the repository
        Transaction savedTransaction = transactionRepository.save(transaction);
        // convert the saved Transaction object to a TransactionDto object
        return transferToDto(savedTransaction);
    }

    // methode to update a single transaction
    public TransactionDto updateTransaction(Long id, TransactionDto updatedTransactionDto) {
        // get the transaction from the repository by ID
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (transactionOptional.isPresent()) {
            // if the transaction exists, get existing transaction object
            Transaction existingTransaction = transactionOptional.get();
            // update existing transaction with new data
            updateTransactionFromDto(existingTransaction, updatedTransactionDto);
            // save updated transaction to repository
            Transaction updatedTransaction = transactionRepository.save(existingTransaction);
            // convert the updated transaction object to a dto object
            return transferToDto(updatedTransaction);
        } else {
            // else throw exception
            throw new TransactionNotFoundException(id);
        }
    }

    // methode to delete a single transaction
    public TransactionDto deleteTransaction(Long id){
        // retrieve the transaction from the repository by id
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        // check if the transaction is present
        if (transactionOptional.isPresent()){
            Transaction transaction = transactionOptional.get();
            // delete the transaction from the repository
            transactionRepository.delete(transaction);
            // convert the transaction object to a transactionDto object
            return transferToDto(transaction);
        } else { // transaction not found: throw a transaction not found exception
            throw new TransactionNotFoundException(id);
        }
    }


    // helper method to convert a Transaction object to a TransactionDto object
    public TransactionDto transferToDto(Transaction transaction){

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

    // helper method to convert a TransactionDto object to a Transaction object
    public Transaction transferToTransaction(TransactionDto transactionDto){

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

    }

    // helper method to update properties of existing transaction object from a transaction dto object
    public void updateTransactionFromDto(Transaction existingTransaction, TransactionDto updatedTransactionDto) {

        // if the property is null, the property will not be changed. if the property is not null, the property will be changed
        if (updatedTransactionDto.getIncome() != null) {
            existingTransaction.setIncome(updatedTransactionDto.getIncome());
        }
        if (updatedTransactionDto.getExpense() != null) {
            existingTransaction.setExpense(updatedTransactionDto.getExpense());
        }
        if (updatedTransactionDto.getAmount() != null) {
            existingTransaction.setAmount(updatedTransactionDto.getAmount());
        }
        if (updatedTransactionDto.getDate() != null) {
            existingTransaction.setDate(updatedTransactionDto.getDate());
        }
        if (updatedTransactionDto.getCategory() != null) {
            existingTransaction.setCategory(updatedTransactionDto.getCategory());
        }
        if (updatedTransactionDto.getPayee() != null) {
            existingTransaction.setPayee(updatedTransactionDto.getPayee());
        }
        if (updatedTransactionDto.getPaymentMethod() != null) {
            existingTransaction.setPaymentMethod(updatedTransactionDto.getPaymentMethod());
        }

    }


}

package com.example.jubileebackendeindopdracht.service;


import com.example.jubileebackendeindopdracht.dto.TransactionDto;
import com.example.jubileebackendeindopdracht.exception.TransactionNotFoundException;
import com.example.jubileebackendeindopdracht.exception.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.model.Account;
import com.example.jubileebackendeindopdracht.model.Transaction;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    // methode to get all transactions from the repository with account id
    public List<TransactionDto> getAllTransactions(){

        List<TransactionDto> transactionDtos = new ArrayList<>();
        List<Transaction> transactions = transactionRepository.findAll();

        for (Transaction transaction : transactions){
            TransactionDto transactionDto = transferTransactionToTransactionDto(transaction);

            Account account = transaction.getAccount();
            if (account != null){
                transactionDto.setAccountId(account.getId());
            }
            transactionDtos.add(transactionDto);
        }
        return transactionDtos;
    }

    // methode to get one single transaction
    public TransactionDto getTransaction(Long id){
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);

        if (transactionOptional.isPresent()){
            Transaction transaction = transactionOptional.get();
            TransactionDto transactionDto = transferTransactionToTransactionDto(transaction);

            Account account = transaction.getAccount();
            if (account != null){
                transactionDto.setAccountId(account.getId());
            }

            return transactionDto;
        } else {
            throw new TransactionNotFoundException(id);
        }
    }

    // methode to create a transaction with account id
    public TransactionDto createTransaction(TransactionDto transactionDto, Long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new UserIdNotFoundException(accountId));

        transactionDto.setAccount(account);

        Transaction transaction = transferTransactionDtoToTransaction(transactionDto);
        Transaction savedTransaction = transactionRepository.save(transaction);

        account.getTransactionList().add(savedTransaction);
        accountRepository.save(account);

        return transferTransactionToTransactionDto(savedTransaction);
    }



    // methode to update a single transaction
    public TransactionDto updateTransaction(Long id, TransactionDto updatedTransactionDto) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        updateTransactionFromDto(existingTransaction, updatedTransactionDto);
        Transaction updatedTransaction = transactionRepository.save(existingTransaction);

        return transferTransactionToTransactionDto(updatedTransaction);
    }

    // delete single transaction
    public TransactionDto deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        transactionRepository.delete(transaction);
        return transferTransactionToTransactionDto(transaction);
    }

    // helper method to convert a Transaction object to a TransactionDto object
    public TransactionDto transferTransactionToTransactionDto(Transaction transaction){

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setType(transaction.getType());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setCategory(transaction.getCategory());
        transactionDto.setPayee(transaction.getPayee());
        transactionDto.setPaymentMethod(transaction.getPaymentMethod());

        Account account = transaction.getAccount();
            if (account != null){
                transactionDto.setAccountId(account.getId());
            }

        return transactionDto;
    }

    // helper method to convert a TransactionDto object to a Transaction object
    public Transaction transferTransactionDtoToTransaction(TransactionDto transactionDto){

        //TODO: relaties toevoegen
        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setType(transactionDto.getType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDate(transactionDto.getDate());
        transaction.setCategory(transactionDto.getCategory());
        transaction.setPayee(transactionDto.getPayee());
        transaction.setPaymentMethod(transactionDto.getPaymentMethod());
        transaction.setAccount(transactionDto.getAccount());

        return transaction;
    }

    // helper method to update (a part of all or all) properties of existing transaction object from a transaction dto object
    public void updateTransactionFromDto(Transaction existingTransaction, TransactionDto updatedTransactionDto) {

        if (updatedTransactionDto.getType() != null) {
            existingTransaction.setType(updatedTransactionDto.getType());
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

package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.dto.TransactionDto;
import com.example.jubileebackendeindopdracht.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // get all transactions
    @GetMapping
    public List<TransactionDto> getAllTransactions(){
        List<TransactionDto> transactionDtoList = transactionService.getAllTransactions();
        return transactionDtoList;
    }

    // get one transaction by id
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable Long id){
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }

    // create transaction
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto){

        Long accountId = transactionDto.getAccountId();

        TransactionDto createdTransactionDto = transactionService.createTransaction(transactionDto, accountId);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTransactionDto.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(createdTransactionDto);
    }

    // update transaction put (fully) /patch (partially)
    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto updatedTransactionDto) {
        TransactionDto updatedTransaction = transactionService.updateTransaction(id, updatedTransactionDto);
        return ResponseEntity.ok(updatedTransaction);
    }

    // delete transaction by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    // calculate
    @GetMapping("/calculate-total-income")
    public ResponseEntity<BigDecimal> calculateTotalIncome(){
        BigDecimal totalIncome = transactionService.calculateTotalIncome();
        return ResponseEntity.ok(totalIncome);
    }

    @GetMapping("/calculate-total-expense")
    public ResponseEntity<BigDecimal> calculateTotalExpense(){
        BigDecimal totalExpense = transactionService.calculateTotalExpense();
        return ResponseEntity.ok(totalExpense);
    }

}

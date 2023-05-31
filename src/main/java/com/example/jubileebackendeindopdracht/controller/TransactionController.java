package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.dto.TransactionDto;
import com.example.jubileebackendeindopdracht.model.Transaction;
import com.example.jubileebackendeindopdracht.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    // variables
    private final TransactionService transactionService;

    // constructor
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // getmapping to get all transactions
    @GetMapping
    public List<TransactionDto> getAllTransactions(){
        // get all transactions using the transactionService
        List<TransactionDto> transactionDtoList = transactionService.getAllTransactions();
        return transactionDtoList;
    }

    // getmapping to get one single transaction
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable Long id){
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }

    // postmapping to create a new transaction
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto){

        // create a transaction using the transaction service methode: create transaction
        TransactionDto createdTransactionDto = transactionService.createTransaction(transactionDto);
        // create the URI for the created transaction
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/transaction" + createdTransactionDto.getId())
                .buildAndExpand().toUriString());
        // return a response entity with the appropriate status and the created transaction dto object
        return ResponseEntity.created(uri).body(createdTransactionDto);
    }


}

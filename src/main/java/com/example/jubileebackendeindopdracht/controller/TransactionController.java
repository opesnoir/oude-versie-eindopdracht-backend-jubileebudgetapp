package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.dto.TransactionDto;
import com.example.jubileebackendeindopdracht.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // getmapping
    @GetMapping
    public List<TransactionDto> getAllTransactions(){
        // get all transactions using the transactionService
        List<TransactionDto> transactionDtoList = transactionService.getAllTransactions();
        return transactionDtoList;
    }

}

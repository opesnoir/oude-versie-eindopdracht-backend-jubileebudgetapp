package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.BalanceDto;
import com.example.jubileebackendeindopdracht.exception.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.model.Balance;
import com.example.jubileebackendeindopdracht.repository.BalanceRepository;
import com.example.jubileebackendeindopdracht.repository.TransactionRepository;
import org.springframework.stereotype.Service;


@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    public BalanceService(BalanceRepository balanceRepository, TransactionRepository transactionRepository, TransactionService transactionService) {
        this.balanceRepository = balanceRepository;
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
    }

    // create
    public BalanceDto createBalance(BalanceDto balanceDto){
        Balance balance = transferBalanceDtoToBalance(balanceDto);
        Balance savedBalance = balanceRepository.save(balance);

        if (savedBalance.getId() == null){
            throw new UserIdNotFoundException();
        }

        if (savedBalance.getBalance() == null){
            throw new RuntimeException("Cannot create balance with no income and expense transactions");
        }

        return transferBalanceToBalanceDto(savedBalance);
    }

    // helper methods
    public Balance transferBalanceDtoToBalance(BalanceDto balanceDto){
        Balance balance = new Balance();

        balance.setId(balanceDto.getId());
        balance.setTotalIncome(transactionRepository.calculateTotalIncome());
        balance.setTotalExpense(transactionRepository.calculateTotalExpense());
        balance.setBalance(transactionService.calculateBalance());

        return balance;
    }

    public BalanceDto transferBalanceToBalanceDto(Balance balance){
        BalanceDto balanceDto = new BalanceDto();

        balanceDto.setId(balance.getId());
        balanceDto.setTotalIncome(transactionRepository.calculateTotalIncome());
        balanceDto.setTotalExpense(transactionRepository.calculateTotalExpense());
        balanceDto.setBalance(transactionService.calculateBalance());

        return balanceDto;
    }


}

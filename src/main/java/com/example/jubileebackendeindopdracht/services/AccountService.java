package com.example.jubileebackendeindopdracht.services;

import com.example.jubileebackendeindopdracht.dtos.AccountDto;
import com.example.jubileebackendeindopdracht.exceptions.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.models.Account;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public AccountService(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }


    // create account
    public AccountDto createAccount(AccountDto accountDto){

        String username = accountDto.getUser().getUsername();
        Account account = transferAccountDtoToAccount(accountDto);
        account.setUsername(username);
        Account savedAccount = accountRepository.save(account);

        return transferAccountToAccountDto(savedAccount);

    }

    // delete account
    public AccountDto deleteAccount(Long id){
        Account account = accountRepository.findById(id)
            .orElseThrow(()-> new UserIdNotFoundException(id));

        accountRepository.delete(account);
        return transferAccountToAccountDto(account);
    }

    // helper methods
    public Account transferAccountDtoToAccount (AccountDto accountDto){
        Account account = new Account();

        account.setDateCreated(LocalDate.now());

        BigDecimal balance = transactionService.calculateBalance();
        if (balance != null){
            account.setBalance(balance);
        }

        account.setUsername(accountDto.getUsername());

        account.setTransactionList(accountDto.getTransactionList());

        return account;
    }

    public AccountDto transferAccountToAccountDto(Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername(account.getUsername());

        accountDto.setDateCreated(LocalDate.now());

        BigDecimal balance = transactionService.calculateBalance();
        if (balance != null){
            accountDto.setBalance(balance);
        }

        accountDto.setTransactionList(account.getTransactionList());

        return accountDto;
    }

}

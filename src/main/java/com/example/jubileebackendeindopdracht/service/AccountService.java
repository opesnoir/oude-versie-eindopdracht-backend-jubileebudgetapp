package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.AccountDto;
import com.example.jubileebackendeindopdracht.exception.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.model.Account;
import com.example.jubileebackendeindopdracht.model.Transaction;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    private final List<Transaction> transactionList;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository, List<Transaction> transactionList) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionList = transactionList;
    }

    // create account
    public AccountDto createAccount(AccountDto accountDto){
        Account account = transferAccountDtoToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return transferAccountToAccountDto(savedAccount);
    }

    //TODO: autorisatie toevoegen dat enkel de admin een account kan verwijderen
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
        account.setId(accountDto.getId());
        account.setBalance(accountDto.getBalance());
        account.setTotalIncome(accountDto.getTotalIncome());
        account.setTotalExpense(accountDto.getTotalExpense());

        account.setTransactionList(accountDto.getTransactionList());

        return account;
    }

    public AccountDto transferAccountToAccountDto(Account account){
        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setBalance(account.getBalance());
        accountDto.setTotalIncome(account.getTotalIncome());
        accountDto.setTotalExpense(account.getTotalExpense());

        accountDto.setTransactionList(account.getTransactionList());

        return accountDto;
    }

}

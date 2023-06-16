package com.example.jubileebackendeindopdracht.controllers;

import com.example.jubileebackendeindopdracht.dtos.AccountDto;
import com.example.jubileebackendeindopdracht.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // create account
    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccountDto = accountService.createAccount(accountDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAccountDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(createdAccountDto);
    }

    // delete transaction by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

}

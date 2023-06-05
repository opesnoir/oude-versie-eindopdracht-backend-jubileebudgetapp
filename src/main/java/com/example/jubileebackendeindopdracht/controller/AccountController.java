package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.dto.AccountDto;
import com.example.jubileebackendeindopdracht.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccountDto = accountService.createAccount(accountDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdAccountDto.getId()).toUriString());
        return ResponseEntity.created(uri).body(createdAccountDto);
    }



}

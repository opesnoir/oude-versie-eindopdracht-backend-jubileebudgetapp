package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.dto.BalanceDto;
import com.example.jubileebackendeindopdracht.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    // create
    @PostMapping
    public ResponseEntity<BalanceDto> createBalance(@RequestBody BalanceDto balanceDto){
        BalanceDto createdBalanceDto = balanceService.createBalance(balanceDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBalanceDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(createdBalanceDto);
    }

}

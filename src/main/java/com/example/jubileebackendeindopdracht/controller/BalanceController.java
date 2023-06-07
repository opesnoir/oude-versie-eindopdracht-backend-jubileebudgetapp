package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.service.BalanceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    // create map
    // update map
    // delete map

}

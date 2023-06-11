package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.dto.SavingGoalDto;
import com.example.jubileebackendeindopdracht.service.SavingGoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/saving_goals")
public class SavingGoalController {

    private final SavingGoalService savingGoalService;

    public SavingGoalController(SavingGoalService savingGoalService) {
        this.savingGoalService = savingGoalService;
    }

    // create saving goal
    @PostMapping
    public ResponseEntity<SavingGoalDto> createSavingGoal(@RequestBody SavingGoalDto savingGoalDto){

        Long accountId = savingGoalDto.getAccountId();

        SavingGoalDto createdSavingGoalDto= savingGoalService.createSavingGoal(savingGoalDto, accountId);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdSavingGoalDto.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(createdSavingGoalDto);
    }

}

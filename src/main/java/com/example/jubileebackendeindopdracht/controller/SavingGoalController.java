package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.dto.SavingGoalDto;
import com.example.jubileebackendeindopdracht.service.SavingGoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/saving_goals")
public class SavingGoalController {

    private final SavingGoalService savingGoalService;

    public SavingGoalController(SavingGoalService savingGoalService) {
        this.savingGoalService = savingGoalService;
    }

    //get all saving goals
    @GetMapping
    public ResponseEntity<List<SavingGoalDto>> getAllSavingGoals(){
        List<SavingGoalDto> savingGoalDtoList = savingGoalService.getAllSavingGoals();

        if (savingGoalDtoList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(savingGoalDtoList, HttpStatus.OK);
    }

    //get saving goal by id
    @GetMapping("/{id}")
    public ResponseEntity<SavingGoalDto> getSavingGoal(@PathVariable Long id) {
        SavingGoalDto savingGoalDto = savingGoalService.getSavingGoalById(id);

        if (savingGoalDto == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(savingGoalDto, HttpStatus.OK);
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

    //delete saving goal by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSavingGoal(@PathVariable Long id){
        savingGoalService.deleteSavingGoal(id);
        return ResponseEntity.noContent().build();
    }
}

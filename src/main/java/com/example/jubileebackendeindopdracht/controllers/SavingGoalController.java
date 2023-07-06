package com.example.jubileebackendeindopdracht.controllers;

import com.example.jubileebackendeindopdracht.dtos.SavingGoalDto;
import com.example.jubileebackendeindopdracht.services.SavingGoalService;
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

    //get saving goal
    @GetMapping("/{id}")
    public ResponseEntity<SavingGoalDto> getSavingGoal(@PathVariable Long id) {
        SavingGoalDto savingGoalDto = savingGoalService.getSavingGoal(id);

        if (savingGoalDto == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(savingGoalDto, HttpStatus.OK);
    }

    //create
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

    //update
    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<SavingGoalDto> updateSavingGoal(@PathVariable Long id, @RequestBody SavingGoalDto updatedSavingGoalDto){
        SavingGoalDto updatedSavingGoal = savingGoalService.updateSavingGoal(id, updatedSavingGoalDto);
        return ResponseEntity.ok(updatedSavingGoal);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSavingGoal(@PathVariable Long id){
        savingGoalService.deleteSavingGoal(id);
        return ResponseEntity.noContent().build();
    }

}

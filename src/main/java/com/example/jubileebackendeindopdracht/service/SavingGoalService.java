package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.SavingGoalDto;
import com.example.jubileebackendeindopdracht.model.SavingGoal;
import com.example.jubileebackendeindopdracht.repository.SavingGoalRepository;
import org.springframework.stereotype.Service;

@Service
public class SavingGoalService {

    private final SavingGoalRepository savingGoalRepository;

    public SavingGoalService(SavingGoalRepository savingGoalRepository) {
        this.savingGoalRepository = savingGoalRepository;
    }

    //get all
    //get one
    //create
    //update
    //delete

    //helper methodes
    public SavingGoalDto transferSavingGoalToSavingGoalDto(SavingGoal savingGoal){

        SavingGoalDto savingGoalDto = new SavingGoalDto();
        savingGoalDto.setId(savingGoal.getId());
        savingGoalDto.setName(savingGoal.getName());
        savingGoalDto.setStartAmount(savingGoal.getStartAmount());
        savingGoalDto.setTargetAmount(savingGoal.getTargetAmount());
        savingGoalDto.setCurrentAmount(savingGoal.getCurrentAmount());
        savingGoalDto.setAmountAdded(savingGoal.getAmountAdded());
        savingGoalDto.setAmountSubtracted(savingGoal.getAmountSubtracted());
        savingGoalDto.setAccount(savingGoal.getAccount());

        return savingGoalDto;
    }

    public SavingGoal transferSavingGoalDtoToSavingGoal(SavingGoalDto savingGoalDto){

        SavingGoal savingGoal = new SavingGoal();
        savingGoal.setId(savingGoalDto.getId());
        savingGoal.setName(savingGoalDto.getName());
        savingGoal.setStartAmount(savingGoalDto.getStartAmount());
        savingGoal.setTargetAmount(savingGoalDto.getTargetAmount());
        savingGoal.setCurrentAmount(savingGoalDto.getCurrentAmount());
        savingGoal.setAmountAdded(savingGoalDto.getAmountAdded());
        savingGoal.setAmountSubtracted(savingGoalDto.getAmountSubtracted());
        savingGoal.setAccount(savingGoalDto.getAccount());

        return savingGoal;
    }

    public void updateTransferSavingGoalToSavingGoalDto(SavingGoal existingSavingGoal, SavingGoalDto updatedSavingGoalDto ){

        if (updatedSavingGoalDto.getName() != null){
            existingSavingGoal.setName(updatedSavingGoalDto.getName());
        }
        if (updatedSavingGoalDto.getStartAmount() != null){
            existingSavingGoal.setStartAmount(updatedSavingGoalDto.getStartAmount());
        }
        if (updatedSavingGoalDto.getTargetAmount() != null){
            existingSavingGoal.setTargetAmount(updatedSavingGoalDto.getTargetAmount());
        }
        if (updatedSavingGoalDto.getCurrentAmount() != null){
            existingSavingGoal.setCurrentAmount(updatedSavingGoalDto.getCurrentAmount());
        }
        if (updatedSavingGoalDto.getAmountAdded() != null){
            existingSavingGoal.setAmountAdded(updatedSavingGoalDto.getAmountAdded());
        }
        if (updatedSavingGoalDto.getAmountSubtracted() != null){
            existingSavingGoal.setAmountSubtracted(updatedSavingGoalDto.getAmountSubtracted());
        }



    }



}

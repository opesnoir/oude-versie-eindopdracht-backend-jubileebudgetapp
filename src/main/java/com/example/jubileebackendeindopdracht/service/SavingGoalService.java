package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.SavingGoalDto;
import com.example.jubileebackendeindopdracht.exception.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.model.Account;
import com.example.jubileebackendeindopdracht.model.SavingGoal;
import com.example.jubileebackendeindopdracht.model.Transaction;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.SavingGoalRepository;
import org.springframework.stereotype.Service;

@Service
public class SavingGoalService {

    private final SavingGoalRepository savingGoalRepository;
    private final AccountRepository accountRepository;

    public SavingGoalService(SavingGoalRepository savingGoalRepository, AccountRepository accountRepository) {
        this.savingGoalRepository = savingGoalRepository;
        this.accountRepository = accountRepository;
    }

    //get all
    //get one
    //create
    public SavingGoalDto createSavingGoal(SavingGoalDto savingGoalDto, Long accountId){

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new UserIdNotFoundException(accountId));
        savingGoalDto.setAccount(account);

        SavingGoal savingGoal = transferSavingGoalDtoToSavingGoal(savingGoalDto);
        SavingGoal savedSavingGoal = savingGoalRepository.save(savingGoal);

        account.getSavingGoalList().add(savedSavingGoal);

        return transferSavingGoalToSavingGoalDto(savingGoal);
    }


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

package com.example.jubileebackendeindopdracht.services;

import com.example.jubileebackendeindopdracht.dtos.SavingGoalDto;
import com.example.jubileebackendeindopdracht.exceptions.RecordNotFoundException;
import com.example.jubileebackendeindopdracht.exceptions.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.models.Account;
import com.example.jubileebackendeindopdracht.models.SavingGoal;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.SavingGoalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO: deze twee verwijderen, enum neemt het over private BigDecimal amountAdded;
//    private BigDecimal amountSubtracted; en dan de desbetreffende enum gebruiken

@Service
public class SavingGoalService {

    private final SavingGoalRepository savingGoalRepository;
    private final AccountRepository accountRepository;

    public SavingGoalService(SavingGoalRepository savingGoalRepository, AccountRepository accountRepository) {
        this.savingGoalRepository = savingGoalRepository;
        this.accountRepository = accountRepository;
    }

    //get all saving goals
    public List<SavingGoalDto> getAllSavingGoals(){
        List<SavingGoalDto> savingGoalDtoList = new ArrayList<>();
        List<SavingGoal> savingGoals = savingGoalRepository.findAll();

        for(SavingGoal savingGoal : savingGoals){
            SavingGoalDto savingGoalDto = transferSavingGoalToSavingGoalDto(savingGoal);

            Account account = savingGoal.getAccount();
            if (account !=null){
                savingGoalDto.setAccountId(account.getId());
            }
            savingGoalDtoList.add(savingGoalDto);
        }

        if (savingGoalDtoList.isEmpty()){
            return Collections.emptyList();
        }
        return savingGoalDtoList;
    }

    //get saving goal
    public SavingGoalDto getSavingGoalById(Long savingGoalId){
        SavingGoal savingGoal = savingGoalRepository.findById(savingGoalId)
                .orElseThrow(() -> new RecordNotFoundException("No saving goal was found for: " + savingGoalId));

        SavingGoalDto savingGoalDto = transferSavingGoalToSavingGoalDto(savingGoal);

        Account account = savingGoal.getAccount();
        if (account !=null) {
            savingGoalDto.setAccountId(account.getId());
        }
        return savingGoalDto;
    }

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
    public SavingGoalDto updateSavingGoal(Long accountId, SavingGoalDto updatedSavingGoalDto){
        SavingGoal existingSavingGoal = savingGoalRepository.findById(accountId)
                .orElseThrow(() -> new UserIdNotFoundException(accountId));

        updateTransferSavingGoalToSavingGoalDto(existingSavingGoal, updatedSavingGoalDto);
        SavingGoal updatedSavingGoal = savingGoalRepository.save(existingSavingGoal);

        return transferSavingGoalToSavingGoalDto(updatedSavingGoal);
    }

    //delete
    public SavingGoalDto deleteSavingGoal(Long savingGoalId){
        SavingGoal savingGoal = savingGoalRepository.findById(savingGoalId)
                .orElseThrow(() -> new RecordNotFoundException("No saving goal was found for: " + savingGoalId));
        SavingGoalDto savingGoalDto = transferSavingGoalToSavingGoalDto(savingGoal);

        savingGoalRepository.delete(savingGoal);
        return savingGoalDto;
    }

    //helper methodes
    public SavingGoalDto transferSavingGoalToSavingGoalDto(SavingGoal savingGoal){

        SavingGoalDto savingGoalDto = new SavingGoalDto();

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

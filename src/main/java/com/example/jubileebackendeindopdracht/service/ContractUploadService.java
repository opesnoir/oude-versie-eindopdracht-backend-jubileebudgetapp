package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.ContractUploadDto;
import com.example.jubileebackendeindopdracht.exception.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.model.Account;
import com.example.jubileebackendeindopdracht.model.ContractUpload;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.ContractUploadRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractUploadService {

    private final ContractUploadRepository contractUploadRepository;
    private final AccountRepository accountRepository;

    public ContractUploadService(ContractUploadRepository contractUploadRepository, AccountRepository accountRepository) {
        this.contractUploadRepository = contractUploadRepository;
        this.accountRepository = accountRepository;
    }

    //upload file
    public ContractUploadDto createContractUpload(ContractUploadDto contractUploadDto, Long accountId){

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new UserIdNotFoundException(accountId));

        contractUploadDto.setAccount(account);
        ContractUpload contractUpload = transferContractUploadDtoToContractUpload(contractUploadDto);
        ContractUpload savedContractUpload = contractUploadRepository.save(contractUpload);

        contractUploadDto.setContractPdfUrl(savedContractUpload.getContractPdfUrl());

        return transferContractUploadToContractUploadDto(savedContractUpload);
    }
    //download file
    //delete file

    //helper methodes
    public ContractUploadDto transferContractUploadToContractUploadDto(ContractUpload contractUpload){

        ContractUploadDto contractUploadDto = new ContractUploadDto();
        contractUploadDto.setId(contractUpload.getId());
        contractUploadDto.setPayee(contractUpload.getPayee());
        contractUploadDto.setContractPurpose(contractUpload.getContractPurpose());
        contractUploadDto.setContractPdfUrl(contractUpload.getContractPdfUrl());

        Account account = contractUpload.getAccount();
        if (account != null){
            contractUploadDto.setAccountId(account.getId());
        }

        return contractUploadDto;
    }

    public ContractUpload transferContractUploadDtoToContractUpload(ContractUploadDto contractUploadDto){

        ContractUpload contractUpload = new ContractUpload();
        contractUpload.setId(contractUploadDto.getId());
        contractUpload.setPayee(contractUploadDto.getPayee());
        contractUpload.setContractPurpose(contractUploadDto.getContractPurpose());
        contractUpload.setContractPdfUrl(contractUploadDto.getContractPdfUrl());

        return contractUpload;
    }

}

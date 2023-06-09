package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.ContractUploadDto;
import com.example.jubileebackendeindopdracht.exception.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.model.Account;
import com.example.jubileebackendeindopdracht.model.ContractUpload;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.ContractUploadRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.Objects;
import java.nio.file.Path;

@Service
public class ContractUploadService {

    @Value("${my.upload_location}")
    private String uploadLocation;

    private final ContractUploadRepository contractUploadRepository;
    private final AccountRepository accountRepository;

    public ContractUploadService(ContractUploadRepository contractUploadRepository, AccountRepository accountRepository) {
        this.contractUploadRepository = contractUploadRepository;
        this.accountRepository = accountRepository;
    }

    // upload file
    public ContractUploadDto createContractUpload(MultipartFile file, ContractUploadDto contractUploadDto, Long accountId) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(uploadLocation, fileName);

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new UserIdNotFoundException(accountId));

        contractUploadDto.setAccount(account);
        ContractUpload contractUpload = transferContractUploadDtoToContractUpload(contractUploadDto);
        ContractUpload savedContractUpload = contractUploadRepository.save(contractUpload);

        return transferContractUploadToContractUploadDto(savedContractUpload);
    }

    // update file
    // download file
    // delete file

    //helper methodes
    public ContractUploadDto transferContractUploadToContractUploadDto(ContractUpload contractUpload){

        ContractUploadDto contractUploadDto = new ContractUploadDto();
        contractUploadDto.setId(contractUpload.getId());
        contractUploadDto.setPayee(contractUpload.getPayee());

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

        return contractUpload;
    }

}

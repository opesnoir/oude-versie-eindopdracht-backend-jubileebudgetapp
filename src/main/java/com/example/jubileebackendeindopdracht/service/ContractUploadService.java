package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.ContractUploadDto;
import com.example.jubileebackendeindopdracht.model.ContractUpload;
import com.example.jubileebackendeindopdracht.repository.ContractUploadRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractUploadService {

    private final ContractUploadRepository contractUploadRepository;

    public ContractUploadService(ContractUploadRepository contractUploadRepository) {
        this.contractUploadRepository = contractUploadRepository;
    }

    //upload file
    //download file
    //delete file

    //helper methodes
    public ContractUploadDto transferContractUploadToContractUploadDto(ContractUpload contractUpload){

        ContractUploadDto contractUploadDto = new ContractUploadDto();
        contractUploadDto.setPayee(contractUpload.getPayee());
        contractUploadDto.setContractPurpose(contractUpload.getContractPurpose());
        contractUploadDto.setContractPdfUrl(contractUpload.getContractPdfUrl());

        return contractUploadDto;
    }

    public ContractUpload transferContractUploadDtoToContractUpload(ContractUploadDto contractUploadDto){

        ContractUpload contractUpload = new ContractUpload();
        contractUpload.setPayee(contractUploadDto.getPayee());
        contractUpload.setContractPurpose(contractUploadDto.getContractPurpose());
        contractUpload.setContractPdfUrl(contractUploadDto.getContractPdfUrl());

        return contractUpload;
    }

}

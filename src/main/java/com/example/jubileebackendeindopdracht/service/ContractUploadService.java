package com.example.jubileebackendeindopdracht.service;

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


}

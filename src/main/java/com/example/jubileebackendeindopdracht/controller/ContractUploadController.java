package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.dto.ContractUploadDto;
import com.example.jubileebackendeindopdracht.model.Account;
import com.example.jubileebackendeindopdracht.service.ContractUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/contractuploads")
public class ContractUploadController {

    private final ContractUploadService contractUploadService;

    public ContractUploadController(ContractUploadService contractUploadService) {
        this.contractUploadService = contractUploadService;
    }

    // upload file
    public ResponseEntity<ContractUploadDto> createContractUpload(@RequestBody ContractUploadDto contractUploadDto){
        Long accountId = contractUploadDto.getAccountId();

        ContractUploadDto createdContractUploadDto = contractUploadService.createContractUpload(contractUploadDto, accountId);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdContractUploadDto.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(createdContractUploadDto);
    }

    // update file
    // download file
    // delete file

}

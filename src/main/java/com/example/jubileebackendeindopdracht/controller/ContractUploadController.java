package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.dto.ContractUploadDto;
import com.example.jubileebackendeindopdracht.service.ContractUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/contractuploads")
public class ContractUploadController {

    private final ContractUploadService contractUploadService;

    public ContractUploadController(ContractUploadService contractUploadService) {
        this.contractUploadService = contractUploadService;
    }

    // upload file
    @PostMapping
    public ResponseEntity<ContractUploadDto> createContractUpload(@RequestParam("file") MultipartFile file, @RequestBody ContractUploadDto contractUploadDto) throws IOException {
        Long accountId = contractUploadDto.getAccountId();

        ContractUploadDto createdContractUploadDto = contractUploadService.createContractUpload(file, contractUploadDto, accountId);

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

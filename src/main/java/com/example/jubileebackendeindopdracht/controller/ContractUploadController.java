package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.service.ContractUploadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contractuploads")
public class ContractUploadController {

    private final ContractUploadService contractUploadService;

    public ContractUploadController(ContractUploadService contractUploadService) {
        this.contractUploadService = contractUploadService;
    }

    // upload file

    // update file
    // download file
    // delete file

}

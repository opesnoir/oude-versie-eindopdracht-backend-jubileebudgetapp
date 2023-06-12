package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.UploadDto;
import com.example.jubileebackendeindopdracht.model.Account;
import com.example.jubileebackendeindopdracht.model.Upload;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.UploadRepository;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
public class UploadService {

    private final UploadRepository uploadRepository;
    private final AccountRepository accountRepository;

    public UploadService(UploadRepository uploadRepository, AccountRepository accountRepository) {
        this.uploadRepository = uploadRepository;
        this.accountRepository = accountRepository;
    }

    // upload file
    public void uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File("/Users/mirre/IdeaProjects/jubilee-backend-eindopdracht/uploads"+file.getOriginalFilename()));

    }
    // download file
    // delete file

    //helper methodes
    public UploadDto transferContractUploadToContractUploadDto(Upload upload){

        UploadDto uploadDto = new UploadDto();
        uploadDto.setId(upload.getId());
        uploadDto.setPayee(upload.getPayee());

        Account account = upload.getAccount();
        if (account != null){
            uploadDto.setAccountId(account.getId());
        }

        return uploadDto;
    }

    public Upload transferContractUploadDtoToContractUpload(UploadDto uploadDto){

        Upload upload = new Upload();
        upload.setId(uploadDto.getId());
        upload.setPayee(uploadDto.getPayee());

        return upload;
    }

}

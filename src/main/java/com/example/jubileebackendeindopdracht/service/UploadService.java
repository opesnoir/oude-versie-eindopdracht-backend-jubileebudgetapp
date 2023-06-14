package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.UploadDto;
import com.example.jubileebackendeindopdracht.exception.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.model.Account;
import com.example.jubileebackendeindopdracht.model.Upload;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.UploadRepository;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
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
    public UploadDto uploadFile(MultipartFile file, Long accountId){
        try{
            Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new UserIdNotFoundException());

            byte[] fileBytes = file.getBytes();

            Upload upload = transferContractUploadDtoToContractUpload(createUploadDto(fileBytes, account));
            Upload savedUpload = uploadRepository.save(upload);
            UploadDto uploadDto = transferContractUploadToContractUploadDto(savedUpload);

            return uploadDto;
        } catch (IOException e){
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    // download file
    public Resource downloadFile(Long fileId) throws FileNotFoundException {
        Upload upload = uploadRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with ID: " + fileId));

        byte[] fileBytes = upload.getUpload();
        String fileName = upload.getFileName();

        return new ByteArrayResource(fileBytes) {
            @Override
            public String getFilename() {
                return fileName;
            }
        };
    }

    // delete file
    public UploadDto deleteFile(Long fileId) throws FileNotFoundException {
        Upload upload = uploadRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with ID: " + fileId));

        uploadRepository.delete(upload);
        return transferContractUploadToContractUploadDto(upload);
    }

    //helper methodes
    public UploadDto transferContractUploadToContractUploadDto(Upload upload){
        UploadDto uploadDto = new UploadDto();

        uploadDto.setId(upload.getId());
        uploadDto.setUpload(upload.getUpload());

        Account account = upload.getAccount();
        if (account != null){
            uploadDto.setAccountId(account.getId());
        }

        return uploadDto;
    }

    public Upload transferContractUploadDtoToContractUpload(UploadDto uploadDto){
        Upload upload = new Upload();

        upload.setId(uploadDto.getId());
        upload.setUpload(uploadDto.getUpload());

        return upload;
    }

    private UploadDto createUploadDto(byte[] fileBytes, Account account){
        UploadDto uploadDto = new UploadDto();

        uploadDto.setUpload(fileBytes);
        uploadDto.setAccount(account);
        uploadDto.setAccountId(account.getId());

        return uploadDto;
    }


}

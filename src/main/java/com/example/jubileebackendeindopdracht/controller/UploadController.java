package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.dto.UploadDto;
import com.example.jubileebackendeindopdracht.service.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.net.URI;


@RestController
@RequestMapping("/uploads")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    // upload
    @PostMapping
    public ResponseEntity<UploadDto> uploadFile(@RequestParam("file")MultipartFile file, @RequestParam("accountId") Long accountId){

        UploadDto uploadedFileDto = uploadService.uploadFile(file, accountId);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uploadedFileDto.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(uploadedFileDto);
    }

    // download file


    // delete file
    @DeleteMapping("/{fileId}")
    public ResponseEntity<UploadDto> deleteFile(@PathVariable("fileId") Long fileId) throws FileNotFoundException {
        UploadDto deletedFileDto = uploadService.deleteFile(fileId);
        return ResponseEntity.ok(deletedFileDto);
    }



}

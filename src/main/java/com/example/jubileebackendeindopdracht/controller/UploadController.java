package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.service.UploadService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/uploads")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    // upload file
    @PostMapping
    public void uploadFile(@RequestParam("file") MultipartFile file ) throws IOException {
        uploadService.uploadFile(file);
    }

    // download file


    // delete file

}

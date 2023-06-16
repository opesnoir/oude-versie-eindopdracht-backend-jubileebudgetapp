package com.example.jubileebackendeindopdracht.exceptions;

import java.io.Serial;

public class FileNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public FileNotFoundException() {
        super();
    }

    public FileNotFoundException(Long fileId) {
        super("File not found with ID: " + fileId);
    }

}

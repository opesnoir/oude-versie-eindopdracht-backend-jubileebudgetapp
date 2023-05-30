package com.example.jubileebackendeindopdracht.exception;

import java.io.Serial;

public class UserIdNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public UserIdNotFoundException(){
        super();
    }

    public UserIdNotFoundException(String id){
        super("Failed to find user with ID: " + id);
    }

}

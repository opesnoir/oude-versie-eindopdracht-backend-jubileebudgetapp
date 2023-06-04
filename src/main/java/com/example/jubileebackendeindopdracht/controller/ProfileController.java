package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.dto.ProfileDto;
import com.example.jubileebackendeindopdracht.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    //variables
    private final ProfileService profileService;

    //constructor
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    //welcome message
    @PostMapping("/welcome")
    public ResponseEntity<String> getWelcomeMessage(@RequestBody ProfileDto profileDto) {
        String welcomeMessage = profileService.getWelcomeMessage(profileDto);
        return ResponseEntity.ok(welcomeMessage);
    }


}

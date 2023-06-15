package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.dto.ProfileDto;
import com.example.jubileebackendeindopdracht.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    //create
    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto){
        Long accountId = profileDto.getAccountId();

        ProfileDto createdProfileDto = profileService.createProfile(profileDto, accountId);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProfileDto.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(createdProfileDto);
    }




}

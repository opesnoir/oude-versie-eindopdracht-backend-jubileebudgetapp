package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    // repository for accessing profile data
    private final ProfileRepository profileRepository;

    // constructor for initializing ProfileService with the corresponding repository
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
}

package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.ProfileDto;
import com.example.jubileebackendeindopdracht.model.Profile;
import com.example.jubileebackendeindopdracht.repository.ProfileRepository;
import org.springframework.stereotype.Service;


@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    //get all
    //get one
    //create

    //update
    //delete



    // helper method to convert a profile object to a profile dto object
    public ProfileDto transferToDto(Profile profile){

        ProfileDto profileDto = new ProfileDto();

        profileDto.setId(profile.getId());
        profileDto.setUsername(profile.getUsername());
        profileDto.setEmailAddress(profile.getEmail());
        profileDto.setBirthDate(profile.getBirthDate());

        return profileDto;
    }

    // helper method to convert a ProfileDto object to a Transaction object
    public Profile transferToProfile(ProfileDto profileDto){

        Profile profile = new Profile();

        profile.setId(profileDto.getId());
        profile.setUsername(profileDto.getUsername());
        profile.setEmail(profileDto.getEmailAddress());
        profile.setBirthDate(profileDto.getBirthDate());

        return profile;
    }

    // helper method to update (a part of all or all) properties of existing transaction object from a transaction dto object
    public void updateProfileFromDto(Profile existingProfile, ProfileDto updatedProfileDto) {

        // if the property is null, the property will not be changed. if the property is not null, the property will be changed
        if (updatedProfileDto.getId() != null) {
            existingProfile.setId(updatedProfileDto.getId());
        }
        if (updatedProfileDto.getUsername() != null) {
            existingProfile.setUsername(updatedProfileDto.getUsername());
        }
        if (updatedProfileDto.getEmailAddress() != null) {
            existingProfile.setEmail(updatedProfileDto.getEmailAddress());
        }
        if (updatedProfileDto.getBirthDate() != null) {
            existingProfile.setBirthDate(updatedProfileDto.getBirthDate());
        }
    }

    public String getWelcomeMessage(ProfileDto profileDto) {
        if (profileDto == null){
            return "Welcome, user!";
        } else {
            return "Hi " + profileDto.getUsername() + "! Welcome to your Jubilee profile page.";
        }
    }

}

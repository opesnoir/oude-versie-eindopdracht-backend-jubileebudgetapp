package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.ProfileDto;
import com.example.jubileebackendeindopdracht.model.Profile;
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

    //TODO: welcome message pas doorgeven bij het aanmaken van account, je wil het maar 1 ker zien het hoeft niet terug gezocht te worden
    public String getWelcomeMessage(ProfileDto profileDto) {
        if (profileDto == null){
            return "Welcome, user!";
        } else {
            return "Hi " + profileDto.getUsername() + "! Welcome to your Jubilee profile page. Here you can see your username, update your username, and remove your account.";

        }
    }


    // helper method to convert a profile object to a profile dto object
    public ProfileDto transferToDto(Profile profile){

        ProfileDto profileDto = new ProfileDto();

        profileDto.setId(profile.getId());
        profileDto.setUsername(profile.getUsername());
        profileDto.setPassword(profile.getPassword());
        profileDto.setEmailAddress(profile.getEmailAddress());
        profileDto.setBirthDate(profile.getBirthDate());

        return profileDto;
    }

    // helper method to convert a ProfileDto object to a Transaction object
    public Profile transferToProfile(ProfileDto profileDto){

        Profile profile = new Profile();

        profile.setId(profileDto.getId());
        profile.setUsername(profileDto.getUsername());
        profile.setPassword(profileDto.getPassword());
        profile.setEmailAddress(profileDto.getEmailAddress());
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
        if (updatedProfileDto.getPassword() != null) {
            existingProfile.setPassword(updatedProfileDto.getPassword());
        }
        if (updatedProfileDto.getEmailAddress() != null) {
            existingProfile.setEmailAddress(updatedProfileDto.getEmailAddress());
        }
        if (updatedProfileDto.getBirthDate() != null) {
            existingProfile.setBirthDate(updatedProfileDto.getBirthDate());
        }
    }
}

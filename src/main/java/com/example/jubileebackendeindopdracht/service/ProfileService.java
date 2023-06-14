package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.ProfileDto;
import com.example.jubileebackendeindopdracht.dto.UploadDto;
import com.example.jubileebackendeindopdracht.exception.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.model.Account;
import com.example.jubileebackendeindopdracht.model.Profile;
import com.example.jubileebackendeindopdracht.model.Upload;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.ProfileRepository;
import org.springframework.stereotype.Service;


@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final AccountRepository accountRepository;
    private final UploadService uploadService;


    public ProfileService(ProfileRepository profileRepository, AccountRepository accountRepository, UploadService uploadService) {
        this.profileRepository = profileRepository;
        this.accountRepository = accountRepository;
        this.uploadService = uploadService;
    }

    //get all
    //get one
    //create
    public ProfileDto createProfile(ProfileDto profileDto, UploadDto uploadDto, Long accountId){
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new UserIdNotFoundException(accountId));
        profileDto.setAccount(account);

        Profile profile = transferProfileDtoToProfile(profileDto);
        Profile savedProfile = profileRepository.save(profile);

        return transferProfileToProfileDto(savedProfile);
    }

    //update
    //delete



    // helper methods
    public ProfileDto transferProfileToProfileDto(Profile profile){

        ProfileDto profileDto = new ProfileDto();

        profileDto.setId(profile.getId());
        profileDto.setUsername(profile.getUsername());
        profileDto.setEmail(profile.getEmail());
        profileDto.setBirthDate(profile.getBirthDate());
        profileDto.setProfileUpload(profile.getProfileUpload());

        return profileDto;
    }


    public Profile transferProfileDtoToProfile(ProfileDto profileDto){

        Profile profile = new Profile();

        profile.setId(profileDto.getId());
        profile.setUsername(profileDto.getUsername());
        profile.setEmail(profileDto.getEmail());
        profile.setBirthDate(profileDto.getBirthDate());
        profile.setProfileUpload(profileDto.getProfileUpload());

        return profile;
    }


    public void updateProfileFromProfileDto(Profile existingProfile, ProfileDto updatedProfileDto) {

        // if the property is null, the property will not be changed. if the property is not null, the property will be changed
        if (updatedProfileDto.getId() != null) {
            existingProfile.setId(updatedProfileDto.getId());
        }
        if (updatedProfileDto.getUsername() != null) {
            existingProfile.setUsername(updatedProfileDto.getUsername());
        }
        if (updatedProfileDto.getEmail() != null) {
            existingProfile.setEmail(updatedProfileDto.getEmail());
        }
        if (updatedProfileDto.getBirthDate() != null) {
            existingProfile.setBirthDate(updatedProfileDto.getBirthDate());
        }
        if (updatedProfileDto.getProfileUpload() != null){
            existingProfile.setProfileUpload(updatedProfileDto.getProfileUpload());
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

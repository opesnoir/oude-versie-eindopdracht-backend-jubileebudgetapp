package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.ProfileDto;
import com.example.jubileebackendeindopdracht.exception.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.model.Account;
import com.example.jubileebackendeindopdracht.model.Profile;
import com.example.jubileebackendeindopdracht.model.User;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.ProfileRepository;
import com.example.jubileebackendeindopdracht.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, AccountRepository accountRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }


    //get all
    //get one
    //create
    public ProfileDto createProfile(ProfileDto profileDto, Long accountId){
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new UserIdNotFoundException(accountId));

        Profile profile = transferProfileDtoToProfile(profileDto);
        Profile savedProfile = profileRepository.save(profile);

        savedProfile.setAccount(account);

        return transferProfileToProfileDto(savedProfile);
    }

    //update
    //delete



    // helper methods
    public ProfileDto transferProfileToProfileDto(Profile profile){

        ProfileDto profileDto = new ProfileDto();

        profileDto.setId(profile.getId());
        profileDto.setUsername(profile.getUser().getUsername());
        profileDto.setEmail(profile.getUser().getEmail());

        if (profile.getAccount() != null) {
            profileDto.setAccountId(profile.getAccount().getId());
        }

        return profileDto;
    }


    public Profile transferProfileDtoToProfile(ProfileDto profileDto){

        Profile profile = new Profile();

        profile.setId(profileDto.getId());
        profile.setUsername(profileDto.getUsername());
        profile.setEmail(profileDto.getEmail());

        User user = userRepository.findById(profileDto.getUserId()).orElseThrow(() -> new UserIdNotFoundException());
        profile.setUser(user);

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
    }

}

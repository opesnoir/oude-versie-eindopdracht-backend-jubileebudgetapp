package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.UserDto;
import com.example.jubileebackendeindopdracht.exception.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.model.*;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }


    //get all
    //get one
    //create
    public UserDto createUser(UserDto userDto, Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new UserIdNotFoundException(accountId));
        userDto.setAccount(account);

        User user = transferUserDtoToUser(userDto);
        User savedUser = userRepository.save(user);

        account.setUser(savedUser);
        accountRepository.save(account);

        return transferUserToUserDto(savedUser);
    }


    //update
    //delete

    //helper methods
    public UserDto transferUserToUserDto(User user){

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setProfile(user.getProfile());

        Account account = user.getAccount();
        if (account != null){
            userDto.setAccountId(account.getId());
        }

        Profile profile = user.getProfile();
        if (profile != null){
            userDto.setProfileId(profile.getId());
        }

        return userDto;
    }

    public User transferUserDtoToUser(UserDto userDto){
        User user = new User();

        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setProfile(userDto.getProfile());
        user.setAccount(userDto.getAccount());

        return user;
    }


    public void updateUserFromUserDto(User existingUser, UserDto updatedUserDto){
        if (updatedUserDto.getId() != null) {
            existingUser.setId(updatedUserDto.getId());
        }
        if (updatedUserDto.getUsername() != null) {
            existingUser.setUsername(updatedUserDto.getUsername());
        }
        if (updatedUserDto.getPassword() != null) {
            existingUser.setPassword(updatedUserDto.getPassword());
        }
        if (updatedUserDto.getEmail() != null) {
            existingUser.setEmail(updatedUserDto.getEmail());
        }
    }




}

package com.example.jubileebackendeindopdracht.service;

import com.example.jubileebackendeindopdracht.dto.UserDto;
import com.example.jubileebackendeindopdracht.exception.UserIdNotFoundException;
import com.example.jubileebackendeindopdracht.model.*;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }


    //get all
    public List<UserDto> getAllUsers(){
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User user : users){
            UserDto userDto = transferUserToUserDto(user);

            Account account = user.getAccount();
            if (account != null){
                userDto.setAccountId(account.getId());
            }

            userDtoList.add(userDto);
        }
        return userDtoList;
    }


    //get user
    public UserDto getUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException(id));

        UserDto userDto = transferUserToUserDto(user);

        Account account = user.getAccount();
        if (account != null){
            userDto.setAccountId(account.getId());
        }
        return userDto;
    }

    //create
    public UserDto createUser(UserDto userDto, Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new UserIdNotFoundException(accountId));

        User user = transferUserDtoToUser(userDto);
        User savedUser = userRepository.save(user);

        savedUser.setAccount(account);

        account.setUser(savedUser);
        accountRepository.save(account);

        return transferUserToUserDto(savedUser);
    }

    //update
    public UserDto updateUser(Long id, UserDto updatedUserDto){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException(id));

        updateUserFromUserDto(existingUser, updatedUserDto);
        User updatedUser = userRepository.save(existingUser);

        return transferUserToUserDto(updatedUser);

    }


    //delete
    public ResponseEntity<UserDto> deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException(id));

        Account account = user.getAccount();
        if (account != null) {
            account.setUser(null);
            accountRepository.save(account);
        }

        userRepository.delete(user);

        return ResponseEntity.noContent().build();
    }


    //helper methods
    public UserDto transferUserToUserDto(User user){

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    public User transferUserDtoToUser(UserDto userDto){
        User user = new User();

        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

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

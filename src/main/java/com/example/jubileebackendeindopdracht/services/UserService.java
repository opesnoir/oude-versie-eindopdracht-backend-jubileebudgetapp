package com.example.jubileebackendeindopdracht.services;

import com.example.jubileebackendeindopdracht.dtos.UserDto;
import com.example.jubileebackendeindopdracht.exceptions.RecordNotFoundException;
import com.example.jubileebackendeindopdracht.exceptions.UsernameNotFoundException;
import com.example.jubileebackendeindopdracht.models.Account;
import com.example.jubileebackendeindopdracht.models.Authority;
import com.example.jubileebackendeindopdracht.models.User;
import com.example.jubileebackendeindopdracht.repository.AccountRepository;
import com.example.jubileebackendeindopdracht.repository.UserRepository;
import com.example.jubileebackendeindopdracht.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }


    //get all
    public List<UserDto> getAllUsers(){
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new RecordNotFoundException("No user data found");
        }

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
    public UserDto getUser(String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        UserDto userDto = transferUserToUserDto(user);

        Account account = user.getAccount();
        if (account != null){
            userDto.setAccountId(account.getId());
        }
        return userDto;
    }

    //create
    public UserDto createUser(UserDto userDto) {
        Long accountId = userDto.getAccountId();

        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        userDto.setEnabled(true);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User newUser = transferUserDtoToUser(userDto);

        if (accountId != null) {
            Account account = new Account();
            account.setId(accountId);
            newUser.setAccount(account);
        }
        userRepository.save(newUser);
        return transferUserToUserDto(newUser);
    }

    //update
    public UserDto updateUser(String username, UserDto updatedUserDto){
        User existingUser = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        updateUserFromUserDto(existingUser.getUsername(), updatedUserDto);

        if (updatedUserDto.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(updatedUserDto.getPassword());
            existingUser.setPassword(encodedPassword);
        }
        User updatedUser = userRepository.save(existingUser);
        return transferUserToUserDto(updatedUser);
    }

    //delete
    public void deleteUser(String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        Account account = user.getAccount();
        if (account != null) {
            accountRepository.delete(account);
        }
        userRepository.delete(user);
    }

    // authority methods
    public Set<Authority> getAuthorities(String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        UserDto userDto = transferUserToUserDto(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        Authority authorityToRemove = user.getAuthorities().stream()
                .filter((a) -> a.getAuthority().equalsIgnoreCase(authority))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Authority not found: " + authority));

        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }


    //helper methods
    public UserDto transferUserToUserDto(User user){
        var userDto = new UserDto();

        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.enabled = user.isEnabled();
        userDto.apikey = user.getApikey();
        userDto.email = user.getEmail();
        userDto.authorities = user.getAuthorities();

        return userDto;
    }

    public User transferUserDtoToUser(UserDto userDto){
        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }

    public void updateUserFromUserDto(String username, UserDto updatedUserDto) {
        if (!userRepository.existsById(username)) {
            throw new UsernameNotFoundException(username);
        }
        User user = userRepository.findById(username).get();

        if (updatedUserDto.getPassword() != null) {
            user.setPassword(updatedUserDto.getPassword());
        }
        if (updatedUserDto.getEnabled() != null) {
            user.setEnabled(updatedUserDto.getEnabled());
        }
        if (updatedUserDto.getEmail() != null) {
            user.setEmail(updatedUserDto.getEmail());
        }
        userRepository.save(user);
    }

}

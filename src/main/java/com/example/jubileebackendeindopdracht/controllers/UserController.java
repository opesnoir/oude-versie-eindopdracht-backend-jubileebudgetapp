package com.example.jubileebackendeindopdracht.controllers;

import com.example.jubileebackendeindopdracht.dtos.UserDto;
import com.example.jubileebackendeindopdracht.exceptions.BadRequestException;
import com.example.jubileebackendeindopdracht.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //get all
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtoList = userService.getAllUsers();
        if (userDtoList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDtoList);
    }

    //get user
    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") String username){
        UserDto userDto = userService.getUser(username);
        if (userDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }

    //create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUserDto = userService.createUser(userDto);
        userService.addAuthority(createdUserDto.getUsername(), "ROLE_USER");

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(createdUserDto.getUsername())
                .toUriString());

        return ResponseEntity.created(uri).body(createdUserDto);
    }

    // update transaction put (fully) /patch (partially)
    @RequestMapping(value = "/{username}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username, @RequestBody UserDto updatedUserDto){
        UserDto updatedUser = userService.updateUser(username, updatedUserDto);
        if (updatedUser == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    //delete
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    // authority
    @GetMapping("/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    @PostMapping("/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping("/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }

}

package com.example.jubileebackendeindopdracht.controller;

import com.example.jubileebackendeindopdracht.dto.UserDto;
import com.example.jubileebackendeindopdracht.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    //get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }


    //create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        Long accountId = userDto.getAccountId();

        UserDto createdUserDto = userService.createUser(userDto, accountId);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUserDto.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(createdUserDto);

    }

    // update transaction put (fully) /patch (partially)
    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@RequestBody UserDto updatedUserDto){
        UserDto updatedUser = userService.updateUser(id, updatedUserDto);
        return ResponseEntity.ok(updatedUser);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}

package com.example.jubileebackendeindopdracht.controllers;

import com.example.jubileebackendeindopdracht.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO: kiezen of je Profile wil uitwerken, dan moet je hem nog aanmaken als entiteit, dto etc.
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

/*
    //get all
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtoList = userService.getAllUsers();
        if (userDtoList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }*/

/*    //get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }*/


    //create
/*    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        Long accountId = userDto.getAccountId();

        UserDto createdUserDto = userService.createUser(userDto, accountId);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUserDto.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(createdUserDto);
    }*/


/*
    // update transaction put (fully) /patch (partially)
    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@RequestBody UserDto updatedUserDto){
        UserDto updatedUser = userService.updateUser(id, updatedUserDto);
        return ResponseEntity.ok(updatedUser);
    }*/

 /*   //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }*/

}

package com.RonitCodes.Blog.Application.Controller;

import com.RonitCodes.Blog.Application.Dto.UserDto;
import com.RonitCodes.Blog.Application.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id,@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getSingleUser/{id}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

}
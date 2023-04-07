package com.BookMyShow.controller;

import com.BookMyShow.dto.UserDto;
import com.BookMyShow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto userDto1=userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserDto> getUser(@RequestParam(value = "user_id",required = true) Long user_id){
        UserDto userDto=userService.getUserInfo(user_id);
        return new ResponseEntity<>(userDto,HttpStatus.OK);

    }

}

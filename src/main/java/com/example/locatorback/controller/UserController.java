package com.example.locatorback.controller;

import com.example.locatorback.models.IncomingUser;
import com.example.locatorback.models.User;
import com.example.locatorback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public String registration(@RequestBody IncomingUser incomingUser){
        boolean request = userService.create(incomingUser);
        if (request){
            return "Success";
        }
        else {
            return "User already exists";
        }
    }
    @GetMapping("/login")
    public String login(){
        User user = userService.findCurrentUser();
        if(user == null){
            return "Error";
        }
        return "Success";
    }

}

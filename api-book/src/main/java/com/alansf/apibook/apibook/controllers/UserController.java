package com.alansf.apibook.apibook.controllers;

import com.alansf.apibook.apibook.models.User;
import com.alansf.apibook.apibook.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("apibook/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

}

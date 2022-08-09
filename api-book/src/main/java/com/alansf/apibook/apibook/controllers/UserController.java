package com.alansf.apibook.apibook.controllers;

import com.alansf.apibook.apibook.models.User;
import com.alansf.apibook.apibook.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/findUserById/{idUser}")
    public Optional<User> findUserById(@PathVariable Integer idUser) {
        return userService.findUserById(idUser);
    }

    @GetMapping("/listAllUsers")
    public List<User> listAllUsers() {
        return userService.listAllUsers();
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{idUser}")
    public void deleteUser(@PathVariable Integer idUser) {
        userService.deleteUserById(idUser);
    }

}

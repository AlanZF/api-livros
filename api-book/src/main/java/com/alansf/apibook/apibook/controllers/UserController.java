package com.alansf.apibook.apibook.controllers;

import com.alansf.apibook.apibook.dtos.request.UserDtoRequest;
import com.alansf.apibook.apibook.dtos.response.UserDtoResponse;
import com.alansf.apibook.apibook.models.User;
import com.alansf.apibook.apibook.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDtoResponse> createUser(@RequestBody UserDtoRequest userDtoRequest) {
        UserDtoResponse response = userService.createUser(userDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listAllUsers")
    public ResponseEntity<List<UserDtoResponse>> listAllUsers() {
        List<UserDtoResponse> response = userService.listAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/findUserById/{idUser}")
    public ResponseEntity<UserDtoResponse> findUserById(@PathVariable Integer idUser) {
        Optional<User> optUser = userService.findUserById(idUser);
        User user = optUser.get();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDtoResponse response = new UserDtoResponse();
        response = modelMapper.map(user, response.getClass());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDtoResponse> updateUser(@RequestBody UserDtoRequest userDtoRequest,
                                                      @PathVariable Integer idUser) {
        Optional<User> optUser = userService.findUserById(idUser);
        User user = optUser.get();

        user.setName(userDtoRequest.getName());
        user.setEmail(userDtoRequest.getEmail());

        UserDtoResponse response = userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/deleteUser/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer idUser) {
        Optional<User> optUser = userService.findUserById(idUser);
        User user = optUser.get();

        userService.deleteUser(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

package com.alansf.apibook.apibook.services;

import com.alansf.apibook.apibook.models.User;
import com.alansf.apibook.apibook.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Integer idUser) {
        return userRepository.findById(idUser);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Integer idUser) {
        userRepository.deleteById(idUser);
    }
}

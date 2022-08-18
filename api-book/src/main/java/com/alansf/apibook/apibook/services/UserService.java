package com.alansf.apibook.apibook.services;

import com.alansf.apibook.apibook.dtos.request.UserDtoRequest;
import com.alansf.apibook.apibook.dtos.response.UserDtoResponse;
import com.alansf.apibook.apibook.models.User;
import com.alansf.apibook.apibook.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDtoResponse createUser(UserDtoRequest userRequest) {
        User user = new User();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        user = modelMapper.map(userRequest, user.getClass());
        userRepository.save(user);

        UserDtoResponse response = new UserDtoResponse();
        response = modelMapper.map(user, response.getClass());

        return response;
    }

    public List<UserDtoResponse> listAllUsers() {
        List<User> users = userRepository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return users.stream().map(user -> modelMapper
                .map(user, UserDtoResponse.class)).collect(Collectors.toList());
    }

    public Optional<User> findUserById(Integer idUser) {
        return userRepository.findById(idUser);
    }

    public UserDtoResponse updateUser(User user) {
        userRepository.save(user);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDtoResponse response = new UserDtoResponse();
        response = modelMapper.map(user, response.getClass());

        return response;
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}

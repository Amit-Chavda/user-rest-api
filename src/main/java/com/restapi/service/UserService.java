package com.restapi.service;

import com.restapi.dto.UserDto;
import com.restapi.entity.User;
import com.restapi.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    public UserService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public UserDto findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return mapper.map(optionalUser.get(), UserDto.class);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void saveAll(List<UserDto> userDtos) {
        List<User> users = Arrays.asList(mapper.map(userDtos, User[].class));
        userRepository.saveAll(users);
    }

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        Type type = new TypeToken<List<UserDto>>() {
        }.getType();
        return mapper.map(users, type);
    }

    public UserDto save(UserDto userDto) {
        if (existsByEmail(userDto.getEmailAddress())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with email " + userDto.getEmailAddress() + " already exists!");
        }
        User user = mapper.map(userDto, User.class);
        userRepository.save(user);
        return userDto;
    }

    public String removeById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with id " + id + " does not exist!");
        }
        userRepository.deleteById(id);
        return "success";
    }

}

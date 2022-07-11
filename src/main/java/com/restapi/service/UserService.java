package com.restapi.service;

import com.restapi.dto.UserDto;
import com.restapi.entity.User;
import com.restapi.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;
    public UserService(UserRepository userRepository,ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper=mapper;
    }

    public UserDto findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return mapper.map(optionalUser.get(),UserDto.class);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void removeById(Long id) {
        userRepository.deleteById(id);
    }

}

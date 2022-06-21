package com.restapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.restapi.entity.User;

public interface UserService {

	User findById(Long id);

	void saveAll(List<User> users);

	List<User> findAll();

	User save(User user);

	boolean existsByEmail(String email);

	void removeById(Long id);

	List<User> search(String keyword);
}

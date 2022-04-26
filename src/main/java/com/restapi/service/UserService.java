package com.restapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.restapi.entity.User;

public interface UserService {

	User findById(Long id);

	void saveAll(List<User> users);

	List<User> findAll();

	User save(User user);

	public boolean existsByEmail(String email);
}

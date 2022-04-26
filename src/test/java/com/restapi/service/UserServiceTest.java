package com.restapi.service;

import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restapi.entity.User;
import com.restapi.repository.UserRepository;
import com.restapi.serviceImpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
@Disabled
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	private UserServiceImpl userService;

	@BeforeEach
	void setUp() {
		userService = new UserServiceImpl(userRepository);
	}

	@AfterEach
	void tearDown() {
		// delete all
	}

	@Test
	@Disabled
	void testFindAllUsers() {
		// when
		userService.findAll();

		// then
		verify(userRepository).findAll();
	}

	@Test
	@Disabled
	public void testFindById() {

		// given
		long id = 100L;

		// when
		userRepository.findById(id);

		// then
		verify(userRepository).findById(id);

	}

}

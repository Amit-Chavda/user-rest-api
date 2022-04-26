package com.restapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.restapi.entity.User;
import org.junit.jupiter.api.Disabled;

@SpringBootTest
@Disabled
class UserRespositotyTest {

	@Autowired
	private UserRepository userRepository;

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Disabled
	void existsByEmail() {
		// given
		User user = new User("TestUser", "TestUser", "test@gmail.com", "test", "test", "test");

		// when
		userRepository.save(user);
		boolean existsUser = userRepository.existsByEmail("test@gmail.com");

		// then
		assertThat(existsUser).isTrue();

	}

}

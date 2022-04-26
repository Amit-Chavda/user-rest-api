package com.restapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.restapi.entity.User;

@SpringBootTest(classes = UserRestApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Disabled
class UserRestAPITests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate template;

	private Calculator calculator = new Calculator();

	@Test
	@Disabled
	void testCreateUser() {
		// given
		User user = new User("TestUser", "TestUser", "test@gmail.com", "test", "test", "test");
 
		// when
		ResponseEntity<User> output = template.postForEntity("http://localhost:" + port + "/User/Save", user,
				User.class);

		// then
		assertThat(output.getStatusCodeValue()).isEqualTo(200);

	}

	@Test
	void testSum() {
		// given
		int n1 = 20;
		int n2 = 25;
		int expected = 45;

		// when
		int result = calculator.doSum(n1, n2);

		// then
		assertThat(result).isEqualTo(expected);

	}

	@Test
	void testproduct() {
		// given
		int n1 = 20;
		int n2 = 25;
		int expected = 500;

		// when
		int result = calculator.doProduct(n1, n2);

		// then
		assertThat(result).isEqualTo(expected);

	}

	@Test
	void testCmpareTwoNumbers() {
		// given
		int n1 = 20;
		int n2 = 25;
		boolean expected = false;

		// when
		boolean result = calculator.compareTwoNums(n1, n2);

		// then
		assertThat(result).isEqualTo(expected);

	}

}

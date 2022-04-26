package com.restapi.conroller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.google.gson.Gson;
import com.restapi.entity.User;

@SpringBootTest
@AutoConfigureMockMvc
@Disabled
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Disabled
	void testGetById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/User/{id}", 5L).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5L));
	}

	@Test
	@Disabled
	void testFindAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/User/All").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
	}

	@Test
	@Disabled
	void testSaveUser() throws Exception {

		// given
		User user = new User("test", "test", "test.test@gmail.com", "test", "test", "test");

		// when
		mockMvc.perform(MockMvcRequestBuilders.post("/User/Save").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(new Gson().toJson(user)))
				.andExpect(status().isOk())
				// then
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test.test@gmail.com"));

	}

}

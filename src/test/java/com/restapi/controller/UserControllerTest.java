package com.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.dto.UserDto;
import com.restapi.entity.User;
import com.restapi.repository.UserRepository;
import com.restapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "/application.properties")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    User user;
    UserDto userDto;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        user = new User();
        user.setEmail("tony@gmail.com");
        user.setAddress("someaddress");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setGender("gender");
        user.setIpAddress("192.168.0.1");

        userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmailAddress(user.getEmail());
        userDto.setPhysicalAddress(user.getAddress());
        userDto.setIpAddress(user.getIpAddress());
    }

    @Test
    void testFindUserById1() throws Exception {
        //Arrange
        user = new User();
        user.setEmail("tony1@gmail.com");
        user.setAddress("someaddress");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setGender("gender");
        user.setIpAddress("192.168.0.1");

        User user1 = userRepository.save(user);


        userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmailAddress(user.getEmail());
        userDto.setPhysicalAddress(user.getAddress());
        userDto.setIpAddress(user.getIpAddress());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/User/" + user1.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        //Act
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //Assert
        resultActions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(userDto)));

    }

    @Test
    void testFindUserById2() throws Exception {
        //Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/User/10");

        //Act
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //Assert
        resultActions
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void testSave() throws Exception {
        //Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/User/Save")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto));

        //Act
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //Assert
        resultActions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(userDto)));
    }


    @Test
    @Sql("classpath:test-data.sql")
    void testFindAll() throws Exception {
        //Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/User/All")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        //Act
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //Assert
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", greaterThan(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].emailAddress", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].physicalAddress", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ipAddress", notNullValue()));


    }

    @Test
    @Sql("classpath:test-data.sql")
    void testDeleteById() throws Exception {
        //Arrange
        User user1 = userRepository.save(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/User/" + user1.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        //Act
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //Assert
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("success"));


    }
}
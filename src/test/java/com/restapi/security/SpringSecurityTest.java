package com.restapi.security;

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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "/application.properties")
public class SpringSecurityTest {
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
    void testFindAll_Unauthorized() throws Exception {
        //Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/User/All")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        //Act
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //Assert
        resultActions
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}
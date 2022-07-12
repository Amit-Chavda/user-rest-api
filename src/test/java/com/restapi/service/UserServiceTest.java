package com.restapi.service;

import com.restapi.dto.UserDto;
import com.restapi.entity.User;
import com.restapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private ModelMapper mapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDto userDto;


    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
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
    void testFindById1() {
        //Arrange
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(userService.findById(any())).thenReturn(userDto);

        //Act
        UserDto userDto1 = userService.findById(1L);

        //Assert
        assertThat(userDto1)
                .isEqualTo(userDto)
                .isNotNull();

    }

    @Test
    void testFindById2() {
        //Arrange
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        //Act and Assert
        assertThrows(ResponseStatusException.class, () -> userService.findById(1L));
    }

    @Test
    void testExistByEmail() {
        //Arrange
        boolean expected = true;
        when(userRepository.existsByEmail(any())).thenReturn(expected);

        //Act
        boolean actual = userService.existsByEmail(user.getEmail());

        //Assert
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void testFindAll() {
        //Arrange
        List<UserDto> expected = Arrays.asList(userDto);
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        when(userService.findAll()).thenReturn(expected);

        //Act
        List<UserDto> actual = userService.findAll();

        //Assert
        assertThat(actual)
                .isEqualTo(expected)
                .hasSize(1)
                .isNotEmpty();
    }

    @Test
    void testRemoveById1() {
        //Arrange
        String expected = "success";
        when(userRepository.existsById(any())).thenReturn(true);

        //Act
        String actual = userService.removeById(1L);

        //Assert
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void testRemoveById2() {
        //Arrange
        when(userRepository.existsById(any())).thenReturn(false);

        //Act and Assert
        assertThrows(ResponseStatusException.class, () -> userService.removeById(1L));
    }

    @Test
    void testSave1() {
        //Arrange
        when(userRepository.existsByEmail(any())).thenReturn(false);

        //Act
        UserDto userDto1 = userService.save(userDto);

        //Assert
        assertThat(userDto1)
                .isNotNull()
                .isEqualTo(userDto);

    }

    @Test
    void testSave2() {
        //Arrange
        when(userRepository.existsByEmail(any())).thenReturn(true);

        //Act and Assert
        assertThrows(ResponseStatusException.class, () -> userService.save(userDto));

    }
}

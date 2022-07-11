package com.restapi.repository;

import com.restapi.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Captor
    ArgumentCaptor<User> argumentCaptor;

    User user;

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
    }

    @Test
    void testFindById1() {
        //Arrange
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        //Act
        Optional<User> optionalUser = userRepository.findById(5L);

        //Assert
        assertThat(optionalUser)
                .isPresent()
                .contains(user);
    }

    @Test
    void testFindById2() {
        //Arrange
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        //Act
        Optional<User> optionalUser = userRepository.findById(5L);

        //Assert
        assertThat(optionalUser).isNotPresent();
    }

    @Test
    void testSave1() {
        //Arrange
        when(userRepository.save(any())).thenReturn(user);


        //Act
        User user1 = userRepository.save(user);

        //Assert
        assertThat(user1).isEqualTo(user);
        verify(userRepository).save(argumentCaptor.capture());
        assertThat(user).isEqualTo(argumentCaptor.getValue());
    }


    @Test
    void testDeleteById() {
        //Arrange
        boolean expected = false;
        doNothing().when(userRepository).deleteById(any());
        when(userRepository.existsById(1L)).thenReturn(expected);

        //Act
        userRepository.deleteById(1L);
        boolean actual = userRepository.existsById(1L);


        //Assert
        assertThat(actual).isEqualTo(expected);
    }


}

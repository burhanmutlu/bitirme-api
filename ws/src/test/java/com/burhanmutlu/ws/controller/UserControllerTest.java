package com.burhanmutlu.ws.controller;

import com.burhanmutlu.ws.entity.Account;
import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;

import java.util.Optional;
import java.util.Spliterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    void createUser() {



        User user = new User();
        user.setName("mock");
        user.setPassword("mock");
        user.setEmail("mock@gmail.com");
        user.setPhoneNumber("05555555555");

        Optional<User> expected = Optional.of(user);


        when(userService.createUser(any())).thenReturn(expected);


        ResponseEntity<User> response = userController.createUser(request);

        User actual = response.getBody();

        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                ()->assertEquals(expected.get(), actual),
                ()->assertEquals(user.getName(), actual.getName())
        );


    }
}
package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;


    @Test
    void getUserByEmail() {

        User user = User.builder().email("burhan@mail.com").build();

        when(userRepository.findByEmail(user.getEmail())).thenReturn(List.of(user));

        List<User> user1 = (List<User>) userRepository.findByEmail(user.getEmail());

        assertEquals("burhan@mail.com", List.of(user));

    }
}
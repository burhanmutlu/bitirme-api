package com.burhanmutlu.ws.user;

import com.burhanmutlu.ws.user.dto.req.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toUser(RegistrationRequest registrationRequest) {
        return User.builder()
                .name(registrationRequest.getName())
                .surname(registrationRequest.getSurname())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .build();
    }

}

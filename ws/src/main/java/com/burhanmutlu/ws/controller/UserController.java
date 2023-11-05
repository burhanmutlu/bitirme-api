package com.burhanmutlu.ws.controller;

import com.burhanmutlu.ws.dto.LoginRequest;
import com.burhanmutlu.ws.dto.RegistrationRequest;
import com.burhanmutlu.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/v1/register")
    public ResponseEntity<Boolean> register(@RequestBody RegistrationRequest registrationRequest) {
        Boolean response = userService.createUser(registrationRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/v1/login")
    public void login(@RequestBody LoginRequest loginRequest) {

    }

}

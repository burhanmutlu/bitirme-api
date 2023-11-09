package com.burhanmutlu.ws.controller;

import com.burhanmutlu.ws.dto.AuthResponse;
import com.burhanmutlu.ws.dto.LoginRequest;
import com.burhanmutlu.ws.dto.RegistrationRequest;
import com.burhanmutlu.ws.security.JwtTokenUtil;
import com.burhanmutlu.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/v1/register")
    public ResponseEntity<Boolean> register(@RequestBody RegistrationRequest registrationRequest) {
        Boolean response = userService.createUser(registrationRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/v1/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        //User user = userService.getUserByEmail(loginRequest.getEmail());
        System.out.println("----"+passwordEncoder.encode("burhan"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }



}

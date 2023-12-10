package com.burhanmutlu.ws.controller;

import com.burhanmutlu.ws.dto.resp.AuthResponse;
import com.burhanmutlu.ws.dto.resp.GenericResponse;
import com.burhanmutlu.ws.dto.req.LoginRequest;
import com.burhanmutlu.ws.dto.req.RegistrationRequest;
import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.security.JwtTokenUtil;
import com.burhanmutlu.ws.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        Boolean response = userService.createUser(registrationRequest);
        // TODO: yabanci dil destegi
        String message = (response) ? "user created" : "user not created";
        return ResponseEntity.ok(new GenericResponse(response, message));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws AuthenticationException {
        User user = userService.getUserByEmail(loginRequest.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),
                        loginRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token, user.getEmail()));
    }

}

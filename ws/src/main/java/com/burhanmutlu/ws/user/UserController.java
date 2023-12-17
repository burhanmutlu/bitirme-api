package com.burhanmutlu.ws.user;

import com.burhanmutlu.ws.shared.LocalMessages;
import com.burhanmutlu.ws.user.dto.resp.AuthResponse;
import com.burhanmutlu.ws.shared.GenericResponse;
import com.burhanmutlu.ws.user.dto.req.LoginRequest;
import com.burhanmutlu.ws.user.dto.req.RegistrationRequest;
import com.burhanmutlu.ws.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
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
        String message = (response) ?
                LocalMessages.get("register.success.message") : LocalMessages.get("register.error.message");
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

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new GenericResponse(true, "user is deleted"));
    }
}

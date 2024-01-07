package com.burhanmutlu.ws.user;

import com.burhanmutlu.ws.shared.LocalMessages;
import com.burhanmutlu.ws.user.dto.req.*;
import com.burhanmutlu.ws.user.dto.resp.AuthResponse;
import com.burhanmutlu.ws.shared.GenericResponse;
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

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;

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
    public ResponseEntity<GenericResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest) throws MessagingException, IOException {
        userService.createUser(registrationRequest);
        String message = LocalMessages.get("register.success.message");
        return ResponseEntity.ok(new GenericResponse(true, message));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws AuthenticationException {
        User user = userService.getUserByEmail(loginRequest.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),
                        loginRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(user.getId(), user.getName(), user.getSurname(), user.getEmail(), token));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new GenericResponse(true, "user is deleted"));
    }

    @PostMapping("/users/password-reset")
    public ResponseEntity<?> passwordResetRequest(@Valid @RequestBody PasswordResetRequest passwordResetRequest)
            throws MessagingException, IOException {
        userService.handleResetRequest(passwordResetRequest);
        return ResponseEntity.ok(new GenericResponse(true, "check your email address to reset your password"));
    }

    @PatchMapping("/users/{token}/password")
    public ResponseEntity<?> setPassword(@PathVariable String token,
                                         @Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest) {

        userService.updatePassword(token, passwordUpdateRequest);
        return ResponseEntity.ok(new GenericResponse(true, "password updated successully"));
    }

    @PatchMapping("/users/{token}/active")
    public ResponseEntity<?> activateUser(@PathVariable String token) throws MessagingException, IOException {
        userService.activateUser(token);
        return ResponseEntity.ok(new GenericResponse(true, "success"));
    }

    @PostMapping("/users/valid-token")
    public ResponseEntity<?> isValidToken(@RequestBody ValidTokenRequest jwtToken) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(jwtToken.getJwtToken()));
        boolean val = jwtTokenUtil.validateToken(jwtToken.getJwtToken(), userDetails);
        return ResponseEntity.ok(new GenericResponse(val,(val==true)?"geçerli":"geçersiz"));
    }


}

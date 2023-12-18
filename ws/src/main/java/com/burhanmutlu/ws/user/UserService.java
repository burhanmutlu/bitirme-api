package com.burhanmutlu.ws.user;

import com.burhanmutlu.ws.user.dto.req.PasswordResetRequest;
import com.burhanmutlu.ws.user.dto.req.PasswordUpdateRequest;
import com.burhanmutlu.ws.user.dto.req.RegistrationRequest;

import javax.mail.MessagingException;
import java.io.IOException;

public interface UserService {

    void createUser(RegistrationRequest registrationRequestDto) throws MessagingException, IOException;

    User getUserByEmail(String email);

    User getUserById(Long id);

    User updateUser(Long userId, RegistrationRequest registrationRequest);

    void deleteUser(Long userId);

    void handleResetRequest(PasswordResetRequest passwordResetRequest) throws MessagingException, IOException;

    void activateUser(String token) throws MessagingException, IOException;

    void updatePassword(String token, PasswordUpdateRequest passwordUpdateRequest);

}

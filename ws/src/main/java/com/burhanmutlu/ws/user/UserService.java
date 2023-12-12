package com.burhanmutlu.ws.user;

import com.burhanmutlu.ws.user.dto.req.RegistrationRequest;

public interface UserService {

    Boolean createUser(RegistrationRequest registrationRequestDto);

    User getUserByEmail(String email);

    User getUserById(Long id);

    User updateUser(Long userId, RegistrationRequest registrationRequest);

    void deleteUser(Long userId);

}

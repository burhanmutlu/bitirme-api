package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.dto.req.RegistrationRequest;
import com.burhanmutlu.ws.entity.User;

public interface UserService {

    Boolean createUser(RegistrationRequest registrationRequestDto);

    User getUserByEmail(String email);

    User getUserById(Long id);

    User updateUser(Long userId, RegistrationRequest registrationRequest);

    void deleteUser(Long userId);

}

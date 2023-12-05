package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.dto.req.RegistrationRequest;
import com.burhanmutlu.ws.entity.User;

public interface UserService {

    public Boolean createUser(RegistrationRequest registrationRequestDto);

    public User getUserByEmail(String email);

    public User updateUser(Long userId, RegistrationRequest registrationRequest);

    public void deleteUser(Long userId);

}

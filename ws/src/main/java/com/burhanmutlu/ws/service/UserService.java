package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.dto.RegistrationRequest;
import com.burhanmutlu.ws.entity.User;

public interface UserService {

    public Boolean createUser(RegistrationRequest registrationRequestDto);

    public User getUserByEmail(String email);

}

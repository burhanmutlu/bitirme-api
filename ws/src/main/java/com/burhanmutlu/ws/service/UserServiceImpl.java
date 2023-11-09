package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.dto.RegistrationRequest;
import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.exception.UserNotFoundException;
import com.burhanmutlu.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service // This way autowired works
@Component("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    //TODO: Do not share any user information with anyone

    /**
     * Creates a new user
     * @param user Is the object of the user to be created
     * @return Returned new user
     */
    @Override
    public Boolean createUser(RegistrationRequest registrationRequest) {
        try {
            User user = User.builder()
                    .name(registrationRequest.getName())
                    .surname(registrationRequest.getSurname())
                    .email(registrationRequest.getEmail())
                    .password(passwordEncoder.encode(registrationRequest.getPassword()))
                    .phoneNumber(registrationRequest.getPhoneNumber())
                    .build();
            userRepository.save(user);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }


    @Override
    public User getUserByEmail(String email) {

        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new UserNotFoundException("User email not found - " + email);
        }

        return user;
    }
}

package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.dto.RegistrationRequest;
import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.exception.UserExceptionHandler;
import com.burhanmutlu.ws.exception.UserNotFoundException;
import com.burhanmutlu.ws.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service // This way autowired works
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    /**
     * Creates a new user
     * @param user Is the object of the user to be created
     * @return Returned new user
     */
    @Override
    public Boolean createUser(RegistrationRequest registrationRequest){
        try {
            User user = User.builder()
                    .name(registrationRequest.getName())
                    .surname(registrationRequest.getSurname())
                    .email(registrationRequest.getEmail())
                    .password(passwordEncoder.encode(registrationRequest.getPassword()))
                    .phoneNumber(registrationRequest.getPhoneNumber())
                    .build();
            userRepository.save(user);
            log.info("created user-email: " + user.getEmail());
            return true;
        } catch (Exception exception) {
            log.error("dont created user- " + exception.getMessage());
            throw new RuntimeException(exception.getMessage());
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

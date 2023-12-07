package com.burhanmutlu.ws.service.impl;

import com.burhanmutlu.ws.dto.req.RegistrationRequest;
import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.exception.UserNotFoundException;
import com.burhanmutlu.ws.repository.UserRepository;
import com.burhanmutlu.ws.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service // This way autowired works
public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a new user
     * @param user Is the object of the user to be created
     * @return Returned new user
     */
    @Override
    public Boolean createUser(RegistrationRequest registrationRequest){
        try {
            // TODO mapper kullan
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

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if(user == null) {
            throw new UserNotFoundException("User id not found - " + id);
        }
        return user;
    }

    @Override
    public User updateUser(Long id, RegistrationRequest registrationRequest) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}

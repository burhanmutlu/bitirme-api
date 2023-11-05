package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.dto.RegistrationRequest;
import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.exception.UserNotFoundException;
import com.burhanmutlu.ws.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service // This way autowired works
<<<<<<< HEAD
@NoArgsConstructor
=======
>>>>>>> 1d9b436d83a84bcb57f070a08b6ee70a5fb85d6c
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        passwordEncoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
    }

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

        User user = (User) userRepository.findByEmail(email);

        if(user == null) {
            throw new UserNotFoundException("User email not found - " + email);
        }

        return user;
    }

}

package com.burhanmutlu.ws.user;

import com.burhanmutlu.ws.email.EmailService;
import com.burhanmutlu.ws.user.dto.req.RegistrationRequest;
import com.burhanmutlu.ws.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service // This way autowired works
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

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
            StringBuilder body = new StringBuilder();
            body.append(user.getName());
            body.append(", uygulamaya kayıt olduğunuz için sizi tebrik ederiz..");
            emailService.sendEmail(user.getEmail(), "Uygulamaya hoş geldiniz :)", body.toString());
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
        User user = userRepository.findById(userId).orElseThrow(()->{
            throw new UserNotFoundException("user not found");
        });
        userRepository.deleteById(userId);
    }
}

package com.burhanmutlu.ws.user;

import com.burhanmutlu.ws.email.EmailService;
import com.burhanmutlu.ws.user.dto.req.PasswordResetRequest;
import com.burhanmutlu.ws.user.dto.req.PasswordUpdateRequest;
import com.burhanmutlu.ws.user.dto.req.RegistrationRequest;
import com.burhanmutlu.ws.user.exception.InvalidTokenException;
import com.burhanmutlu.ws.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private EmailService emailService;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    /**
     * Creates a new user
     * @param user Is the object of the user to be created
     * @return Returned new user
     */
    @Override
    public void createUser(RegistrationRequest registrationRequest) throws MessagingException, IOException {
        User user = userMapper.toUser(registrationRequest);
        user.setActivationToken(UUID.randomUUID().toString());
        userRepository.saveAndFlush(user);
        emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());
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

    @Override
    public void handleResetRequest(PasswordResetRequest passwordResetRequest) throws MessagingException, IOException {
        User user = getUserByEmail(passwordResetRequest.getEmail());
        if(user == null) throw new UserNotFoundException("user not found");
        user.setPasswordResetToken(UUID.randomUUID().toString());
        userRepository.save(user);
        emailService.sendPasswordResetEmail(user.getEmail(), user.getPasswordResetToken());
    }

    @Override
    public void activateUser(String token) throws MessagingException, IOException {
        User user = userRepository.findByActivationToken(token);
        if(user == null) {
            throw new InvalidTokenException();
        }
        user.setActive(true);
        user.setActivationToken(null);
        userRepository.save(user);
        emailService.sendWelcomeEmail(user.getEmail());
    }

    @Override
    public void updatePassword(String token, PasswordUpdateRequest passwordUpdateRequest) {
        User user = userRepository.findByPasswordResetToken(token);
        if(user == null) {
            throw new InvalidTokenException();
        }
        user.setPasswordResetToken(null);
        user.setPassword(passwordEncoder.encode(passwordUpdateRequest.getPassword()));
        user.setActive(true);
        userRepository.save(user);
    }
}

package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.exception.UserNotFoundException;
import com.burhanmutlu.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // This way autowired works
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this();
        this.userRepository = userRepository;
    }

    //TODO: Do not share any user information with anyone

    /**
     * Creates a new user
     * @param user Is the object of the user to be created
     * @return Returned new user
     */
    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> result = userRepository.findById(id);

        User user = null;
        if(result.isPresent()) {
            user = result.get();
        } else {
            throw new UserNotFoundException("User id not found - " + id);
        }

        return user;
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}

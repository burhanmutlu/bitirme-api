package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.exception.UserNotFoundException;
import com.burhanmutlu.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // bu sayede autowired calisiyor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
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

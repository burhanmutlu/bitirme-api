package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User createUser(User user);

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public List<User> getUsersByName(String name);

    public void deleteUserById(Long id);

}

package com.burhanmutlu.ws.controller;

import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.exception.UserNotFoundException;
import com.burhanmutlu.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/v1/users")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping("/v1/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/v1/users/{name}")
    public List<User> getUsersByName(@PathVariable String name) {
        return userService.getUsersByName(name);
    }

    @GetMapping("/v1/user/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId) {

        Optional<User> user = userService.getUserById(userId);

        if( (userId >= userService.getAllUsers().size()) || (userId < 0) || user.isEmpty() )
            throw new UserNotFoundException("User id not found - " + userId);
        return user;
    }

}

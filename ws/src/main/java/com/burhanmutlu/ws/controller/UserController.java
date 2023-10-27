package com.burhanmutlu.ws.controller;

import com.burhanmutlu.ws.entity.User;
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

    @GetMapping("/v1/users/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId) {
        return userService.getById(userId);
    }

    @RequestMapping(value = "/v1/users")
    public User getByName(@RequestParam(value="userName") String userName) {
        return userService.getByName(userName);
    }


}

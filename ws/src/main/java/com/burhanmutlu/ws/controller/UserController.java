package com.burhanmutlu.ws.controller;

import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/v1/users")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping("/v1/users")
    public void updateUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @DeleteMapping("/v1/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

    @GetMapping("/v1/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/v1/users/{name}", method = RequestMethod.GET)
    public List<User> getUsersByName(@PathVariable String name) {
        return userService.getUsersByName(name);
    }

    @GetMapping("/v1/user/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return  userService.getUserById(userId);
    }

}

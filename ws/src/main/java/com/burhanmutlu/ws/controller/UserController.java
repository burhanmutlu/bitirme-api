package com.burhanmutlu.ws.controller;

import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.exception.ErrorResponse;
import com.burhanmutlu.ws.exception.UserNotFoundException;
import com.burhanmutlu.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/v1/users")
    public User getByName(@RequestParam(value="userName") String userName) {
        return userService.getByName(userName);
    }

    @GetMapping("/v1/users/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId) {

        if( (userId >= userService.getAllUsers().size()) || (userId < 0) ) {
            throw new UserNotFoundException("User id not found - " + userId);
        }

        return userService.getById(userId);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException exception) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}

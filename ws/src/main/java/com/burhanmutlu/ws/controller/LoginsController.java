package com.burhanmutlu.ws.controller;

import com.burhanmutlu.ws.dto.req.LoginRequest;
import com.burhanmutlu.ws.dto.req.LoginsRequest;
import com.burhanmutlu.ws.dto.resp.LoginsResponse;
import com.burhanmutlu.ws.service.LoginsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class LoginsController {

    @Autowired
    private LoginsService loginsService;

    @GetMapping("/logins/user/{userId}")
    public ResponseEntity<List<LoginsResponse>> getAllLoginsByUserId(@PathVariable  Long userId) {
        List<LoginsResponse> loginsResponseList = loginsService.getAllLoginsByUserId(userId);
        return ResponseEntity.ok(loginsResponseList);
    }

    @GetMapping("/logins/{id}")
    public ResponseEntity<LoginsResponse> getLoginsById(@PathVariable Long id) {
        LoginsResponse loginsResponse = loginsService.getLoginsById(id);
        return ResponseEntity.ok(loginsResponse);
    }

    @PostMapping("/logins/user/{userId}")
    public ResponseEntity<LoginsResponse> addLoginsByUserId(@PathVariable Long userId,
                                                            @RequestBody LoginsRequest loginsRequest) {
        LoginsResponse loginsResponse = loginsService.addLoginsByUserId(userId, loginsRequest);
        return ResponseEntity.ok(loginsResponse);
    }


}

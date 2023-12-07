package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.dto.req.LoginRequest;
import com.burhanmutlu.ws.dto.req.LoginsRequest;
import com.burhanmutlu.ws.dto.resp.LoginsResponse;

import java.util.List;

public interface LoginsService {

    List<LoginsResponse> getAllLoginsByUserId(Long id);

    LoginsResponse getLoginsById(Long id);

    LoginsResponse addLoginsByUserId(Long id, LoginsRequest loginsRequest);

    LoginsService updateLogins(Long id, LoginRequest loginRequest);

    void deleteLogins(Long id);

}

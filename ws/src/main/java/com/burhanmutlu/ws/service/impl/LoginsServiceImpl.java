package com.burhanmutlu.ws.service.impl;

import com.burhanmutlu.ws.dto.CompanyDto;
import com.burhanmutlu.ws.dto.req.LoginRequest;
import com.burhanmutlu.ws.dto.req.LoginsRequest;
import com.burhanmutlu.ws.dto.resp.LoginsResponse;
import com.burhanmutlu.ws.entity.Company;
import com.burhanmutlu.ws.entity.Logins;
import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.repository.LoginsRepository;
import com.burhanmutlu.ws.service.CompanyService;
import com.burhanmutlu.ws.service.LoginsService;
import com.burhanmutlu.ws.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginsServiceImpl implements LoginsService {

    private static final Logger log = LogManager.getLogger(LoginsServiceImpl.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginsRepository loginsRepository;

    @Override
    public List<LoginsResponse> getAllLoginsByUserId(Long id) {
        List<LoginsResponse> loginsResponseList = new ArrayList<>();

        loginsRepository.findAllByUserId(id).forEach(logins1 -> {
            Long cId = logins1.getCompanyId().getId();
            LoginsResponse loginsResponse = LoginsResponse.builder()
                    .id(logins1.getId())
                    .username(logins1.getUsername())
                    .password(logins1.getPassword())
                    .companyData(companyService.getCompanyById(cId))
                    .build();
            loginsResponseList.add(loginsResponse);
        });

        return loginsResponseList;
    }

    @Override
    public LoginsResponse getLoginsById(Long id) {
        //TODO exception
        Logins logins = loginsRepository.findById(id).orElseThrow(
                () -> {throw new RuntimeException("not found"); });

        Long cId = logins.getCompanyId().getId();

        LoginsResponse loginsResponse = LoginsResponse.builder()
                .id(logins.getId())
                .username(logins.getUsername())
                .password(logins.getPassword())
                .companyData(companyService.getCompanyById(cId))
                .build();

        return loginsResponse;
    }

    @Override
    public LoginsResponse addLoginsByUserId(Long id, LoginsRequest loginsRequest) {
        User user = userService.getUserById(id);
        CompanyDto companyDto = companyService.getCompanyById(loginsRequest.getCompanyId());
        Company company = Company.builder()
                .id(companyDto.getId())
                .companyName(companyDto.getCompanyName())
                .companyLogo(companyDto.getCompanyLogo())
                .companyWebPage(companyDto.getCompanyWebPage())
                .build();

        Logins logins = Logins.builder()
                .userId(user)
                .username(loginsRequest.getUsername())
                .password(loginsRequest.getPassword())
                .companyId(company)
                .build();

        loginsRepository.save(logins);

        LoginsResponse loginsResponse = LoginsResponse.builder()
                .id(logins.getId())
                .username(logins.getUsername())
                .password(logins.getPassword())
                .companyData(companyDto)
                .build();

        return loginsResponse;
    }

    @Override
    public LoginsService updateLogins(Long id, LoginRequest loginRequest) {
        return null;
    }

    @Override
    public void deleteLogins(Long id) {

    }
}

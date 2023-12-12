package com.burhanmutlu.ws.logins;

import com.burhanmutlu.ws.company.dto.resp.CompanyResponse;
import com.burhanmutlu.ws.logins.dto.req.LoginsRequest;
import com.burhanmutlu.ws.logins.dto.resp.LoginsResponse;
import com.burhanmutlu.ws.company.Company;
import com.burhanmutlu.ws.user.User;
import com.burhanmutlu.ws.company.CompanyService;
import com.burhanmutlu.ws.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginsServiceImpl implements LoginsService {

    private static final Logger log = LogManager.getLogger(LoginsServiceImpl.class);

    private final CompanyService companyService;

    private final UserService userService;

    private final LoginsRepository loginsRepository;

    @Override
    public List<LoginsResponse> getAllLoginsByUserId(Long id, int page, int size, String sortBy, String sortOrder) {
        User user = userService.getUserById(id);
        List<LoginsResponse> loginsResponseList = new ArrayList<>();

        Sort.Direction direction = Sort.Direction.ASC;

        if(sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        loginsRepository.findAllByUserId(id, pageable).forEach(logins1 -> {
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
        CompanyResponse companyResponse = companyService.getCompanyById(loginsRequest.getCompanyId());
        Company company = Company.builder()
                .id(companyResponse.getId())
                .companyName(companyResponse.getCompanyName())
                .companyLogo(companyResponse.getCompanyLogo())
                .companyWebPage(companyResponse.getCompanyWebPage())
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
                .companyData(companyResponse)
                .build();

        return loginsResponse;
    }

    @Override
    public LoginsResponse updateLogins(Long id, LoginsRequest loginsRequest) {
        Logins logins = loginsRepository.findById(id).orElseThrow(() -> {throw new RuntimeException("not found"); });
        User user = userService.getUserById(logins.getUserId().getId());

        CompanyResponse companyResponse = companyService.getCompanyById(loginsRequest.getCompanyId());
        Company company = Company.builder()
                .id(companyResponse.getId())
                .companyName(companyResponse.getCompanyName())
                .companyLogo(companyResponse.getCompanyLogo())
                .companyWebPage(companyResponse.getCompanyWebPage())
                .build();


        logins = Logins.builder()
                .id(id)
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
                .companyData(companyResponse)
                .build();

        return loginsResponse;
    }

    @Override
    public void deleteLogins(Long id) {
        Logins logins = loginsRepository.findById(id).orElseThrow(() -> {throw new RuntimeException("not found"); });
        loginsRepository.deleteById(id);
    }
}

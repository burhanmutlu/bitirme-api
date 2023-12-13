package com.burhanmutlu.ws.logins;

import com.burhanmutlu.ws.logins.dto.req.LoginsRequest;
import com.burhanmutlu.ws.logins.dto.resp.LoginsResponse;
import com.burhanmutlu.ws.logins.exception.LoginsNotFoundException;
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

    private final LoginsMapper loginsMapper;

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
            loginsResponseList.add(loginsMapper.toLoginsResponse(logins1)); });

        return loginsResponseList;
    }

    @Override
    public LoginsResponse getLoginsById(Long id) {
        //TODO exception
        Logins logins = loginsRepository.findById(id).orElseThrow(
                () -> {throw new LoginsNotFoundException("Logins not found"); });

        return loginsMapper.toLoginsResponse(logins);
    }

    @Override
    public LoginsResponse addLoginsByUserId(Long id, LoginsRequest loginsRequest) {
        User user = userService.getUserById(id);

        Logins logins = loginsMapper.toLogins(loginsRequest);
        logins.setUserId(user);

        logins = loginsRepository.save(logins);

        return loginsMapper.toLoginsResponse(logins);
    }

    @Override
    public LoginsResponse updateLogins(Long id, LoginsRequest loginsRequest) {
        Logins logins = loginsRepository.findById(id).orElseThrow(
                () -> {throw new LoginsNotFoundException("Logins not found"); });

        User user = userService.getUserById(logins.getUserId().getId());

        logins = loginsMapper.toLogins(loginsRequest);
        logins.setUserId(user); logins.setId(id);

        loginsRepository.save(logins);

        return loginsMapper.toLoginsResponse(logins);
    }

    @Override
    public void deleteLogins(Long id) {
        Logins logins = loginsRepository.findById(id).orElseThrow(
                () -> {throw new LoginsNotFoundException("Logins not found"); });
        loginsRepository.deleteById(id);
    }
}

package com.burhanmutlu.ws.logins;

import com.burhanmutlu.ws.company.Company;
import com.burhanmutlu.ws.company.CompanyService;
import com.burhanmutlu.ws.company.dto.resp.CompanyResponse;
import com.burhanmutlu.ws.logins.dto.req.LoginsRequest;
import com.burhanmutlu.ws.logins.dto.resp.LoginsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginsMapper {

    @Autowired
    private CompanyService companyService;

    public LoginsResponse toLoginsResponse(Logins logins) {
        Long cId = logins.getCompanyId().getId();

        return LoginsResponse.builder()
                .id(logins.getId())
                .username(logins.getUsername())
                .password(logins.getPassword())
                .companyData(companyService.getCompanyById(cId))
                .build();
    }

    public Logins toLogins(LoginsRequest loginsRequest) {
        CompanyResponse companyResponse = companyService.getCompanyById(loginsRequest.getCompanyId());

        Company company = Company.builder()
                .id(companyResponse.getId())
                .companyName(companyResponse.getCompanyName())
                .companyLogo(companyResponse.getCompanyLogo())
                .companyWebPage(companyResponse.getCompanyWebPage())
                .build();

        return Logins.builder()
                .username(loginsRequest.getUsername())
                .password(loginsRequest.getPassword())
                .companyId(company)
                .build();
    }

}

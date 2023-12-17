package com.burhanmutlu.ws.company;

import com.burhanmutlu.ws.company.dto.req.CompanyRequest;
import com.burhanmutlu.ws.company.dto.resp.CompanyResponse;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyResponse toCompanyResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .companyLogo(company.getCompanyLogo())
                .companyWebPage(company.getCompanyWebPage())
                .isUpdatable(true)
                .build();
    }

    public Company toCompany(CompanyRequest companyRequest) {
        return Company.builder()
                .companyName(companyRequest.getCompanyName())
                .companyLogo(companyRequest.getCompanyLogo())
                .companyWebPage(companyRequest.getCompanyWebPage())
                .build();
    }

}

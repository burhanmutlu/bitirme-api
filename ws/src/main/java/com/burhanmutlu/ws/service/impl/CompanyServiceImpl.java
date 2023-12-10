package com.burhanmutlu.ws.service.impl;

import com.burhanmutlu.ws.dto.resp.CompanyResponse;
import com.burhanmutlu.ws.dto.req.CompanyRequest;
import com.burhanmutlu.ws.entity.Company;
import com.burhanmutlu.ws.exception.CompanyNotFoundException;
import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.repository.CompanyRepository;
import com.burhanmutlu.ws.service.CompanyService;
import com.burhanmutlu.ws.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private static final Logger log = LogManager.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;

    private final UserService userService;

    @Override
    public List<CompanyResponse> getAllCompaniesByUserId(Long id) {
        User user = userService.getUserById(id);

        List<Company> companies = companyRepository.findAllByUserId(id);

        List<CompanyResponse> companyResponseList = new ArrayList<>();
        boolean updatable;
        for(Company company : companies) {
            updatable = true;

            if(company.getUserId().getId() == 1) {
                updatable = false;
            }
            CompanyResponse companyResponse = CompanyResponse.builder()
                    .id(company.getId())
                    .companyName(company.getCompanyName())
                    .companyLogo(company.getCompanyLogo())
                    .companyWebPage(company.getCompanyWebPage())
                    .updatable(updatable)
                    .build();
            companyResponseList.add(companyResponse);
        }
        return companyResponseList;
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> { throw new CompanyNotFoundException("Company not found"); });
        boolean updatable = true;
        if(company.getUserId().getId() == 1) {
            updatable = false;
        }

        CompanyResponse companyResponse = CompanyResponse.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .companyLogo(company.getCompanyLogo())
                .updatable(updatable)
                .companyWebPage(company.getCompanyWebPage()).build();

        return companyResponse;
    }

    @Override
    public CompanyResponse addCompanyByUserId(CompanyRequest request, Long userId) {

        User user = userService.getUserById(userId);

        Company company = Company.builder()
                .companyName(request.getCompanyName())
                .companyLogo(request.getCompanyLogo())
                .companyWebPage(request.getCompanyWebPage())
                .userId(user)
                .build();

        company = companyRepository.save(company);

        CompanyResponse companyResponse = CompanyResponse.builder()
                .companyName(company.getCompanyName())
                .companyWebPage(company.getCompanyWebPage())
                .companyLogo(company.getCompanyLogo())
                .id(userId)
                .updatable(true)
                .build();

        return companyResponse;
    }

    @Override
    public CompanyResponse updateCompany(CompanyRequest companyRequest, Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> { throw new CompanyNotFoundException("company not found"); });

        if(company.getUserId().getId() == 1) {
            throw new RuntimeException("You do not have the authority to change");
        }

        company = Company.builder()
                .companyName(companyRequest.getCompanyName())
                .companyLogo(companyRequest.getCompanyLogo())
                .companyWebPage(companyRequest.getCompanyWebPage())
                .id(id)
                .build();

        companyRepository.save(company);

        CompanyResponse companyResponse = CompanyResponse.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .companyLogo(company.getCompanyLogo())
                .companyWebPage(company.getCompanyWebPage())
                .updatable(true)
                .build();

        return companyResponse;
    }

    @Override
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        if(company == null || company.getUserId().getId() == 1) {
            throw new RuntimeException("company not found or You do not have the authority to change");
        }
        companyRepository.deleteById(id);
    }
}

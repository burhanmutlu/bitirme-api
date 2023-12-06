package com.burhanmutlu.ws.service.impl;

import com.burhanmutlu.ws.dto.CompanyDto;
import com.burhanmutlu.ws.dto.req.CompanyRequest;
import com.burhanmutlu.ws.model.Company;
import com.burhanmutlu.ws.exception.CompanyNotFoundException;
import com.burhanmutlu.ws.model.User;
import com.burhanmutlu.ws.repository.CompanyRepository;
import com.burhanmutlu.ws.service.CompanyService;
import com.burhanmutlu.ws.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger log = LogManager.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<CompanyDto> getAllCompaniesByUserId(Long id) {
        User user = userService.getUserById(id);

        List<Company> companies = companyRepository.findAllByUserId(id);

        List<CompanyDto> companyDtoList = new ArrayList<>();
        for(Company company : companies) {
            CompanyDto companyDto = CompanyDto.builder()
                    .id(company.getId())
                    .companyName(company.getCompanyName())
                    .companyLogo(company.getCompanyLogo())
                    .companyWebPage(company.getCompanyWebPage())
                    .build();
            companyDtoList.add(companyDto);
        }
        return companyDtoList;
    }

    @Override
    public CompanyDto getCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> { throw new CompanyNotFoundException("Company not found"); });

        CompanyDto companyDto = CompanyDto.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .companyLogo(company.getCompanyLogo())
                .companyWebPage(company.getCompanyWebPage()).build();

        return companyDto;
    }

    @Override
    public Company addCompanyById(CompanyRequest request, Long userId) {

        User user = userService.getUserById(userId);

        Company company = Company.builder()
                .companyName(request.getCompanyName())
                .companyLogo(request.getCompanyLogo())
                .companyWebPage(request.getCompanyWebPage())
                .userId(user)
                .build();


        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Company updatedCompany) {
        return null;
    }

    @Override
    public void deleteCompany(Long id) {
        getCompanyById(id);
        companyRepository.deleteById(id);
    }
}

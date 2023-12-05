package com.burhanmutlu.ws.service.impl;

import com.burhanmutlu.ws.dto.req.CompanyRequest;
import com.burhanmutlu.ws.entity.Company;
import com.burhanmutlu.ws.exception.CompanyNotFoundException;
import com.burhanmutlu.ws.exception.UserNotFoundException;
import com.burhanmutlu.ws.repository.CompanyRepository;
import com.burhanmutlu.ws.service.CompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger log = LogManager.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> { throw new CompanyNotFoundException("Company not found"); });
        return company;
    }

    @Override
    public Company addCompany(CompanyRequest request) {
        return null;
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

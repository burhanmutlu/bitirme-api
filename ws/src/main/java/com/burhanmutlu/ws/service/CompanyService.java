package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.entity.Company;

import java.util.List;

public interface CompanyService {

    public List<Company> getAllCompanies();

    public Company getCompanyById(Long id);

    public Company addCompany(Company company);

    public Company updateCompany(Company updatedCompany);

}

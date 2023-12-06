package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.dto.CompanyDto;
import com.burhanmutlu.ws.dto.req.CompanyRequest;
import com.burhanmutlu.ws.model.Company;

import java.util.List;

public interface CompanyService {

    public List<CompanyDto> getAllCompaniesByUserId(Long id);

    public CompanyDto getCompanyById(Long id);

    Company addCompanyById(CompanyRequest request, Long userId);

    public Company updateCompany(Company updatedCompany);

    public void deleteCompany(Long id);

}

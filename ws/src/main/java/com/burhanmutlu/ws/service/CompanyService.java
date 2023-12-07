package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.dto.CompanyDto;
import com.burhanmutlu.ws.dto.req.CompanyRequest;
import com.burhanmutlu.ws.entity.Company;

import java.util.List;

public interface CompanyService {

    List<CompanyDto> getAllCompaniesByUserId(Long id);

    CompanyDto getCompanyById(Long id);

    CompanyDto addCompanyByUserId(CompanyRequest request, Long userId);

    CompanyDto updateCompany(CompanyRequest companyRequest, Long id);

    void deleteCompany(Long id);

}

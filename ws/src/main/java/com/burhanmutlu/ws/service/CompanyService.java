package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.dto.resp.CompanyResponse;
import com.burhanmutlu.ws.dto.req.CompanyRequest;

import java.util.List;

public interface CompanyService {

    List<CompanyResponse> getAllCompaniesByUserId(Long id);

    CompanyResponse getCompanyById(Long id);

    CompanyResponse addCompanyByUserId(CompanyRequest request, Long userId);

    CompanyResponse updateCompany(CompanyRequest companyRequest, Long id);

    void deleteCompany(Long id);

}

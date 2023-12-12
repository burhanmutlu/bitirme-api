package com.burhanmutlu.ws.company;

import com.burhanmutlu.ws.company.dto.resp.CompanyResponse;
import com.burhanmutlu.ws.company.dto.req.CompanyRequest;

import java.util.List;

public interface CompanyService {

    List<CompanyResponse> getAllCompaniesByUserId(Long id);

    CompanyResponse getCompanyById(Long id);

    CompanyResponse addCompanyByUserId(CompanyRequest request, Long userId);

    CompanyResponse updateCompany(CompanyRequest companyRequest, Long id);

    void deleteCompany(Long id);

}

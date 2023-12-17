package com.burhanmutlu.ws.company;

import com.burhanmutlu.ws.company.exception.CompanyNotFoundException;
import com.burhanmutlu.ws.company.dto.resp.CompanyResponse;
import com.burhanmutlu.ws.company.dto.req.CompanyRequest;
import com.burhanmutlu.ws.user.User;
import com.burhanmutlu.ws.user.UserService;
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

    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyResponse> getAllCompaniesByUserId(Long id) {
        User user = userService.getUserById(id);

        List<Company> companies = companyRepository.findAllByUserId(id);

        List<CompanyResponse> companyResponseList = new ArrayList<>();
        boolean updatable;
        for(Company company : companies) {
            updatable = true;

            if(company.getUserId().getId() == 1)
                updatable = false;

            CompanyResponse companyResponse = companyMapper.toCompanyResponse(company);
            companyResponse.setIsUpdatable(updatable);

            companyResponseList.add(companyResponse);
        }
        return companyResponseList;
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> { throw new CompanyNotFoundException("Company not found"); });

        boolean updatable = true;
        if(company.getUserId().getId() == 1)
            updatable = false;

        CompanyResponse companyResponse = companyMapper.toCompanyResponse(company);
        companyResponse.setIsUpdatable(updatable);

        return companyResponse;
    }

    @Override
    public CompanyResponse addCompanyByUserId(CompanyRequest request, Long userId) {
        User user = userService.getUserById(userId);

        Company company = companyMapper.toCompany(request);
        company.setUserId(user);

        company = companyRepository.save(company);

        return companyMapper.toCompanyResponse(company);
    }

    @Override
    public CompanyResponse updateCompany(CompanyRequest companyRequest, Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> { throw new CompanyNotFoundException("company not found"); });

        if(company.getUserId().getId() == 1)
            throw new RuntimeException("You do not have the authority to change");

        company = companyMapper.toCompany(companyRequest);
        company.setId(id);

        company = companyRepository.save(company);

        return companyMapper.toCompanyResponse(company);
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

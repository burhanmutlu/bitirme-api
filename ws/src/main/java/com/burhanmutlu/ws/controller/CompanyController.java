package com.burhanmutlu.ws.controller;

import com.burhanmutlu.ws.dto.CompanyDto;
import com.burhanmutlu.ws.dto.resp.GenericResponse;
import com.burhanmutlu.ws.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies/user/{userId}")
    public ResponseEntity<List<CompanyDto>> getAllCompaniesByUserId(@PathVariable Long userId) {
        List<CompanyDto> companyDtoList = companyService.getAllCompaniesByUserId(userId);
        return ResponseEntity.ok(companyDtoList);
    }

    @GetMapping("/companies/{companyId}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long companyId) {
        CompanyDto companyDto = companyService.getCompanyById(companyId);
        return ResponseEntity.ok(companyDto);
    }

    @DeleteMapping("/companies/{companyId}")
    public ResponseEntity<?> deleteCompanyById(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.ok(new GenericResponse(true, "company is deleted"));
    }


}

package com.burhanmutlu.ws.controller;

import com.burhanmutlu.ws.dto.resp.GenericResponse;
import com.burhanmutlu.ws.entity.Company;
import com.burhanmutlu.ws.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        //return new ResponseEntity<>(company, HttpStatus.OK);
        //return ResponseEntity.status(200).body(company);
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/companies/{companyId}")
    public ResponseEntity<?> deleteCompanyById(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.ok(new GenericResponse(true, "company is deleted"));
    }


}

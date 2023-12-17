package com.burhanmutlu.ws.company;

import com.burhanmutlu.ws.company.dto.resp.CompanyResponse;
import com.burhanmutlu.ws.company.dto.req.CompanyRequest;
import com.burhanmutlu.ws.shared.GenericResponse;
import com.burhanmutlu.ws.shared.LocalMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/companies/user/{userId}")
    public ResponseEntity<List<CompanyResponse>> getAllCompaniesByUserId(@PathVariable Long userId) {
        List<CompanyResponse> companyResponseList = companyService.getAllCompaniesByUserId(userId);
        return ResponseEntity.ok(companyResponseList);
    }

    @GetMapping("/companies/{companyId}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long companyId) {
        CompanyResponse companyResponse = companyService.getCompanyById(companyId);
        return ResponseEntity.ok(companyResponse);
    }

    @PostMapping("/companies/user/{userId}")
    public ResponseEntity<CompanyResponse> addCompany(@RequestBody CompanyRequest companyRequest,
                                                      @PathVariable Long userId) {
        CompanyResponse companyResponse = companyService.addCompanyByUserId(companyRequest, userId);
        return ResponseEntity.ok(companyResponse);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable Long id,
                                                         @RequestBody CompanyRequest companyRequest) {
        CompanyResponse companyResponse = companyService.updateCompany(companyRequest, id);
        return ResponseEntity.ok(companyResponse);
    }


    @DeleteMapping("/companies/{companyId}")
    public ResponseEntity<?> deleteCompanyById(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.ok(new GenericResponse(true, LocalMessages.get("company.delete.success.message")));
    }


}

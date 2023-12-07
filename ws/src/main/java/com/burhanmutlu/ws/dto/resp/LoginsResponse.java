package com.burhanmutlu.ws.dto.resp;

import com.burhanmutlu.ws.dto.CompanyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginsResponse {
    private Long id;
    private String username;
    private String password;
    private CompanyDto companyData;

}

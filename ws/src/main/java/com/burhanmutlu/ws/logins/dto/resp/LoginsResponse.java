package com.burhanmutlu.ws.logins.dto.resp;

import com.burhanmutlu.ws.company.dto.resp.CompanyResponse;
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
    private CompanyResponse companyData;

}

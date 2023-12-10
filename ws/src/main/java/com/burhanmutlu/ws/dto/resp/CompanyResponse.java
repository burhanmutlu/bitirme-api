package com.burhanmutlu.ws.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyResponse {

    private Long id;

    private String companyName;

    private String companyLogo;

    private String companyWebPage;

    private Boolean updatable;


}

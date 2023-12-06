package com.burhanmutlu.ws.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyDto {

    private Long id;

    private String companyName;

    private String companyLogo;

    private String companyWebPage;


}

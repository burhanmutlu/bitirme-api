package com.burhanmutlu.ws.dto.req;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CompanyRequest {

    @NotNull
    private String companyName;

    private String companyLogo;

    private String companyWebPage;

}

package com.burhanmutlu.ws.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CompanyRequest {

    @NotNull
    private String companyName;

    // @JsonProperty("company_logo") bunu baska bir yerden veri cekersek kullaniriz eslesme icin
    private String companyLogo;

    private String companyWebPage;

}

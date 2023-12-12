package com.burhanmutlu.ws.logins.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginsRequest {

    @NotNull
    private Long companyId;

    @NotNull
    private String username;

    @NotNull
    private String password;
}

package com.burhanmutlu.ws.dto.req;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

}

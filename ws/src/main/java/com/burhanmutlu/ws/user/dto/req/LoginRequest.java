package com.burhanmutlu.ws.user.dto.req;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

    @Email
    private String email;

    @NotNull
    private String password;

}

package com.burhanmutlu.ws.user.dto.req;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class PasswordResetRequest {

    @Email
    private String email;

}

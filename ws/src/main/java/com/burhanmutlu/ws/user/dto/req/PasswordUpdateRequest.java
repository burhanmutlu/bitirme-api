package com.burhanmutlu.ws.user.dto.req;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class PasswordUpdateRequest {

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;

}

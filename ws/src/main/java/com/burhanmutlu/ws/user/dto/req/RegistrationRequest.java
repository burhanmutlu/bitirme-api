package com.burhanmutlu.ws.user.dto.req;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationRequest {

    @NotNull
    @Size(min = 1, max = 40)
    private String name;

    @NotNull
    private String surname;

    @NotNull(message = "{constraint.email.notnull.message}")
    @Email(message = "hatalı")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+.=])(?=\\S+$).{8,16}$")
    private String password;

}

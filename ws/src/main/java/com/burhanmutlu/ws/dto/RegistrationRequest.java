package com.burhanmutlu.ws.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegistrationRequest {

    @NotNull
    @Size(min = 1, max = 40)
    private String name;

    @NotNull
    private String surname;

    @NotNull(message = "{constraint.email.notnull.message}")
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;

}

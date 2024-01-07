package com.burhanmutlu.ws.user.dto.req;

import lombok.Data;

@Data
public class ValidTokenRequest {
    private String jwtToken;
}

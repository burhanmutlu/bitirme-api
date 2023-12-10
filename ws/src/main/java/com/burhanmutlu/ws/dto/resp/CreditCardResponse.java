package com.burhanmutlu.ws.dto.resp;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreditCardResponse {

    private Long id;

    private String cardName;

    private String cardNumber;

    private String cardHolderName;

    private String expirationDate;

    private String cvv;

}

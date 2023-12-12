package com.burhanmutlu.ws.creditCard.dto.req;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditCardRequest {

    private String cardName;

    private String cardNumber;

    private String cardHolderName;

    private String expirationDate;

    private String cvv;

}

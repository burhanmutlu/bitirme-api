package com.burhanmutlu.ws.creditcard.dto.resp;

import lombok.Builder;
import lombok.Data;

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

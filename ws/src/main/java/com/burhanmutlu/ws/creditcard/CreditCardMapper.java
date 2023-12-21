package com.burhanmutlu.ws.creditcard;

import com.burhanmutlu.ws.creditcard.dto.req.CreditCardRequest;
import com.burhanmutlu.ws.creditcard.dto.resp.CreditCardResponse;
import org.springframework.stereotype.Component;

@Component
public class CreditCardMapper {

    public CreditCard toCreditCard(CreditCardRequest creditCardRequest) {
        return CreditCard.builder()
                .cardName(creditCardRequest.getCardName())
                .cardNumber(creditCardRequest.getCardNumber())
                .cardHolderName(creditCardRequest.getCardHolderName())
                .cvv(creditCardRequest.getCvv())
                .expirationDate(creditCardRequest.getExpirationDate())
                .build();
    }

    public CreditCardResponse toCreditCardResponse(CreditCard creditCard) {
        return CreditCardResponse.builder()
                .cardName(creditCard.getCardName())
                .cardHolderName(creditCard.getCardHolderName())
                .cardNumber(creditCard.getCardNumber())
                .cvv(creditCard.getCvv())
                .expirationDate(creditCard.getExpirationDate())
                .id(creditCard.getId())
                .build();
    }

}

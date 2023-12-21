package com.burhanmutlu.ws.creditcard;

import com.burhanmutlu.ws.creditcard.dto.req.CreditCardRequest;
import com.burhanmutlu.ws.creditcard.dto.resp.CreditCardResponse;

import java.util.List;

public interface CreditCardService {

    List<CreditCardResponse> getAllCreditCardsByUserId(Long id);

    CreditCardResponse getCreditCardById(Long id);

    CreditCardResponse addCreditCardByUserId(Long userId, CreditCardRequest creditCardRequest);

    CreditCardResponse updateCreditCard(Long id, CreditCardRequest creditCardRequest);

    void deleteCreditCard(Long id);

}

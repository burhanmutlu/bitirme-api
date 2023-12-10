package com.burhanmutlu.ws.service.impl;

import com.burhanmutlu.ws.dto.req.CreditCardRequest;
import com.burhanmutlu.ws.dto.resp.CreditCardResponse;
import com.burhanmutlu.ws.service.CreditCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private static final Logger log = LogManager.getLogger(CreditCardServiceImpl.class);

    @Override
    public List<CreditCardResponse> getAllCreditCardsByUserId(Long id) {
        return null;
    }

    @Override
    public CreditCardResponse getCreditCardById(Long id) {
        return null;
    }

    @Override
    public CreditCardResponse addCreditCardByUserId(Long userId, CreditCardRequest creditCardRequest) {
        return null;
    }

    @Override
    public CreditCardResponse updateCreditCard(Long userId, CreditCardRequest creditCardRequest) {
        return null;
    }

    @Override
    public void deleteCreditCard(Long id) {

    }
}

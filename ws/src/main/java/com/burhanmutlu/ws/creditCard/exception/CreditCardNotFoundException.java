package com.burhanmutlu.ws.creditCard.exception;

public class CreditCardNotFoundException extends RuntimeException{
    public CreditCardNotFoundException(String message) {
        super(message);
    }
}

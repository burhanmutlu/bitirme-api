package com.burhanmutlu.ws.creditcard.exception;

public class CreditCardNotFoundException extends RuntimeException{
    public CreditCardNotFoundException(String message) {
        super(message);
    }
}

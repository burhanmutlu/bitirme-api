package com.burhanmutlu.ws.exception;

public class CreditCardNotFoundException extends RuntimeException{
    public CreditCardNotFoundException(String message) {
        super(message);
    }
}

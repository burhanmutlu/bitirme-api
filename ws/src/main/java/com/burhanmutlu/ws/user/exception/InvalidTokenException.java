package com.burhanmutlu.ws.user.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("hatalı token");
    }
}

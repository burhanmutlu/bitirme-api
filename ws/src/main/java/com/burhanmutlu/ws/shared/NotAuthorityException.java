package com.burhanmutlu.ws.shared;

public class NotAuthorityException extends RuntimeException {
    public NotAuthorityException() {
        super("Hata!!! Bunu yapmak için yetkiniz yok");
    }
}

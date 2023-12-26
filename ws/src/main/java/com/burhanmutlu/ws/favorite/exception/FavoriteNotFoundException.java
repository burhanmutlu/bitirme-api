package com.burhanmutlu.ws.favorite.exception;

public class FavoriteNotFoundException extends RuntimeException {
    public FavoriteNotFoundException() {
        super("Favorite not found");
    }
}

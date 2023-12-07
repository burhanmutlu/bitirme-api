package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.entity.Favorite;
import com.burhanmutlu.ws.entity.User;

import java.util.List;

public interface FavoriteService {

    List<Favorite> getFavoritesByUser(User user);

    Favorite getFavoriteById(Long id);

    Favorite addFileToFavorite(Long userId, Long fileId);

    Favorite addLoginsToFavorite(Long userId, Long loginsId);

    void deleteFavorite(Long id);


}

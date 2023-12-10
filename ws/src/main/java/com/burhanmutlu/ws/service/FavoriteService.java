package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.dto.req.FavoriteRequest;
import com.burhanmutlu.ws.entity.Favorite;
import com.burhanmutlu.ws.entity.User;

import java.util.List;

public interface FavoriteService {

    List<Favorite> getAllFavoritesByUser(User user);

    Favorite getFavoriteById(Long id);

    Favorite addFileToFavorite(Long userId, FavoriteRequest favoriteRequest);

    Favorite addLoginsToFavorite(Long userId, FavoriteRequest favoriteRequest);

    void deleteFavorite(Long id);


}

package com.burhanmutlu.ws.favorite;

import com.burhanmutlu.ws.favorite.dto.req.FavoriteRequest;
import com.burhanmutlu.ws.user.User;

import java.util.List;

public interface FavoriteService {

    List<Favorite> getAllFavoritesByUser(User user);

    Favorite getFavoriteById(Long id);

    Favorite addFileToFavorite(Long userId, FavoriteRequest favoriteRequest);

    Favorite addLoginsToFavorite(Long userId, FavoriteRequest favoriteRequest);

    void deleteFavorite(Long id);


}

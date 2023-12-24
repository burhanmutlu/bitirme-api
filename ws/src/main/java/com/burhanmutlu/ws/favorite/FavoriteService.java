package com.burhanmutlu.ws.favorite;

import com.burhanmutlu.ws.favorite.dto.req.FavoriteRequest;
import com.burhanmutlu.ws.favorite.dto.resp.FavoriteResponse;
import com.burhanmutlu.ws.user.User;

import java.util.List;

public interface FavoriteService {

    List<FavoriteResponse> getAllFavoritesLoginsByUser(User userId);
    List<FavoriteResponse> getAllFavoritesFilesByUser(Long userId);

    FavoriteResponse getFavoriteById(Long id);

    Favorite addFileToFavorite(Long userId, FavoriteRequest favoriteRequest);

    Favorite addLoginsToFavorite(Long userId, FavoriteRequest favoriteRequest);

    void deleteFavorite(Long id);


}

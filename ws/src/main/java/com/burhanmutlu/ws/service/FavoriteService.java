package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.model.Favorite;
import com.burhanmutlu.ws.model.User;

import java.util.List;

public interface FavoriteService {

    public List<Favorite> getFavoritesByUser(User user);

    public Favorite getFavoriteById(Long id);

    public Favorite addFileToFavorite(Long userId, Long fileId);

    public Favorite addLoginsToFavorite(Long userId, Long loginsId);

    public void deleteFavorite(Long id);


}

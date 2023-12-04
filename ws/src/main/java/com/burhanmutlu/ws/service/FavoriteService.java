package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.entity.Favorite;
import com.burhanmutlu.ws.entity.File;
import com.burhanmutlu.ws.entity.Logins;
import com.burhanmutlu.ws.entity.User;

import java.util.List;

public interface FavoriteService {

    public List<Favorite> getFavoritesByUser(User user);

    public Favorite getFavoriteById(Long id);

    public Favorite addFileToFavorite(Long userId, Long fileId);

    public Favorite addLoginsToFavorite(Long userId, Long loginsId);

    public void deleteFavorite(Long id);


}

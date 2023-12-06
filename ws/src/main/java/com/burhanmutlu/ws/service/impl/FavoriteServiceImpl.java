package com.burhanmutlu.ws.service.impl;

import com.burhanmutlu.ws.model.Favorite;
import com.burhanmutlu.ws.model.User;
import com.burhanmutlu.ws.service.FavoriteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private static final Logger log = LogManager.getLogger(FavoriteServiceImpl.class);


    @Override
    public List<Favorite> getFavoritesByUser(User user) {
        return null;
    }

    @Override
    public Favorite getFavoriteById(Long id) {
        return null;
    }

    @Override
    public Favorite addFileToFavorite(Long userId, Long fileId) {
        return null;
    }

    @Override
    public Favorite addLoginsToFavorite(Long userId, Long loginsId) {
        return null;
    }

    @Override
    public void deleteFavorite(Long id) {

    }
}

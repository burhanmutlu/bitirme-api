package com.burhanmutlu.ws.service.impl;

import com.burhanmutlu.ws.dto.req.FavoriteRequest;
import com.burhanmutlu.ws.entity.Favorite;
import com.burhanmutlu.ws.entity.FavoriteFile;
import com.burhanmutlu.ws.entity.FavoriteLogins;
import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.repository.FavoriteRepository;
import com.burhanmutlu.ws.service.FavoriteService;
import com.burhanmutlu.ws.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private static final Logger log = LogManager.getLogger(FavoriteServiceImpl.class);

    private final FavoriteRepository favoriteRepository;

    private final UserService userService;

    @Override
    public List<Favorite> getAllFavoritesByUser(User user) {
        return null;
    }

    @Override
    public Favorite getFavoriteById(Long id) {
        return null;
    }

    @Override
    public Favorite addFileToFavorite(Long userId, FavoriteRequest favoriteRequest) {
        User user = userService.getUserById(userId);

        FavoriteFile favoriteFile = new FavoriteFile();
        favoriteFile.setFavoriteId(favoriteRequest.getFavoriteId());
        favoriteFile.setUserId(user);

        return favoriteRepository.save(favoriteFile);
    }

    @Override
    public Favorite addLoginsToFavorite(Long userId, FavoriteRequest favoriteRequest) {
        User user = userService.getUserById(userId);

        FavoriteLogins favoriteLogins = new FavoriteLogins();
        favoriteLogins.setFavoriteId(favoriteRequest.getFavoriteId());
        favoriteLogins.setUserId(user);

        return favoriteRepository.save(favoriteLogins);
    }

    @Override
    public void deleteFavorite(Long id) {

    }
}

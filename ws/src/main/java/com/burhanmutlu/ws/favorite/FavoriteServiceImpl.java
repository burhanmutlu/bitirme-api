package com.burhanmutlu.ws.favorite;

import com.burhanmutlu.ws.favorite.child.FavoriteFile;
import com.burhanmutlu.ws.favorite.child.FavoriteLogins;
import com.burhanmutlu.ws.favorite.dto.req.FavoriteRequest;
import com.burhanmutlu.ws.favorite.dto.resp.FavoriteResponse;
import com.burhanmutlu.ws.user.User;
import com.burhanmutlu.ws.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private static final Logger log = LogManager.getLogger(FavoriteServiceImpl.class);

    private final FavoriteRepository favoriteRepository;

    private final UserService userService;

    private final FavoriteMapper favoriteMapper;


    @Override
    public List<FavoriteResponse> getAllFavoritesLoginsByUser(User user) {
        return null;
    }

    @Override
    public List<FavoriteResponse> getAllFavoritesFilesByUser(Long userId) {
        User user = userService.getUserById(userId);
        List<FavoriteResponse> favoriteList = new ArrayList<>();
        favoriteRepository.findAllByUserId(user.getId()).forEach(favorite -> {
            favoriteList.add(favoriteMapper.toFavoriteResponse(favorite));
        });
        return favoriteList;
    }

    @Override
    public FavoriteResponse getFavoriteById(Long id) {
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

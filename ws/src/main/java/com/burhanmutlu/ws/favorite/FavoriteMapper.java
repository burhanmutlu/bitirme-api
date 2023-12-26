package com.burhanmutlu.ws.favorite;

import com.burhanmutlu.ws.favorite.dto.resp.FavoriteResponse;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FavoriteMapper {

    public FavoriteResponse toFavoriteResponse(Favorite favorite) {
        return FavoriteResponse.builder()
                .id(favorite.getId())
                .favoriteId(favorite.getFavoriteId())
                .createdAt(favorite.getCreatedAt())
                .build();
    }

}

package com.burhanmutlu.ws.favorite.dto.resp;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FavoriteResponse {
    private Long id;
    private Date createdAt;
    private String favoriteId;
}

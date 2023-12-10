package com.burhanmutlu.ws.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FavoriteRequest {

    @NotNull
    private Long favoriteId;

}

package com.burhanmutlu.ws.favorite.child;

import com.burhanmutlu.ws.favorite.Favorite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FILE")
public class FavoriteFile extends Favorite {
}

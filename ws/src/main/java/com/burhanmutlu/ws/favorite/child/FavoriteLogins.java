package com.burhanmutlu.ws.favorite.child;

import com.burhanmutlu.ws.favorite.Favorite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LOGINS")
public class FavoriteLogins extends Favorite {
}

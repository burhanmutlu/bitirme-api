package com.burhanmutlu.ws.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LOGINS")
public class FavoriteLogins extends Favorite{
}

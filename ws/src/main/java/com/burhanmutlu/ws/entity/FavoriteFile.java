package com.burhanmutlu.ws.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FILE")
public class FavoriteFile extends Favorite{
}

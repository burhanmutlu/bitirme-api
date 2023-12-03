package com.burhanmutlu.ws.entity;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("PASSWORD")
public class FavoritePassword extends Favorite {

    @ManyToOne
    @JoinColumn(name = "password_id", nullable = false)
    private Password password;

}

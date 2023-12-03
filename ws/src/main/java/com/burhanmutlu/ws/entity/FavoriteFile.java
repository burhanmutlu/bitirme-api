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
@DiscriminatorValue("FILE")
public class FavoriteFile extends Favorite{

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File file;

}

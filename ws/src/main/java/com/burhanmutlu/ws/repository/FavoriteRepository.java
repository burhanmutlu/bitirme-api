package com.burhanmutlu.ws.repository;

import com.burhanmutlu.ws.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}

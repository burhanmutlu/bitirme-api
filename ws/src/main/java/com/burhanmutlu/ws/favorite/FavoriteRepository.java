package com.burhanmutlu.ws.favorite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query(value = "select * from favorites where favorite_type = 'FILE' and user_id = :userId", nativeQuery = true)
    List<Favorite> findAllByUserId(@Param("userId") Long userId);
}

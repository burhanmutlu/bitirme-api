package com.burhanmutlu.ws.logins;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginsRepository extends JpaRepository<Logins, Long> {

    @Query("SELECT l FROM Logins l JOIN l.userId u WHERE u.id = :userId")
    public List<Logins> findAllByUserId(@Param("userId") Long userId, Pageable pageable);

}

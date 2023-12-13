package com.burhanmutlu.ws.creditCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {
    @Query("SELECT c FROM CreditCard c JOIN c.userId u WHERE u.id = :userId")
    List<CreditCard> findAllByUserId(@Param("userId") Long userId);
}

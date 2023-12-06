package com.burhanmutlu.ws.repository;

import com.burhanmutlu.ws.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT c FROM Company c JOIN c.userId u WHERE u.id = :userId or u.id = 1")
    public List<Company> findAllByUserId(@Param("userId") Long userId);

}

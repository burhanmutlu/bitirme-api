package com.burhanmutlu.ws.repository;

import com.burhanmutlu.ws.entity.Company;
import com.burhanmutlu.ws.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
    @Query("SELECT f FROM File f JOIN f.userId u WHERE u.id = :userId")
    public List<File> findAllByUserId(@Param("userId") Long userId);
}

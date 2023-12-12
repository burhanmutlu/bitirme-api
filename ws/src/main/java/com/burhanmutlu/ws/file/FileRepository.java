package com.burhanmutlu.ws.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
    @Query("SELECT f FROM File f JOIN f.userId u WHERE u.id = :userId")
    public List<File> findAllByUserId(@Param("userId") Long userId);
}

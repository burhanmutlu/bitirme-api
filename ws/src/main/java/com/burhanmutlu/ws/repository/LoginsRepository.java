package com.burhanmutlu.ws.repository;

import com.burhanmutlu.ws.model.Logins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginsRepository extends JpaRepository<Logins, Long> {
}

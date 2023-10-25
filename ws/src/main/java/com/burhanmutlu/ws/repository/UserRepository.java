package com.burhanmutlu.ws.repository;

import com.burhanmutlu.ws.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

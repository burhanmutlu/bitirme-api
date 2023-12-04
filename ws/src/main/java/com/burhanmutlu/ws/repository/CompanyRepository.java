package com.burhanmutlu.ws.repository;

import com.burhanmutlu.ws.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}

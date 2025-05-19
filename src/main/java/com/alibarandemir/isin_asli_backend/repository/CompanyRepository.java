package com.alibarandemir.isin_asli_backend.repository;

import com.alibarandemir.isin_asli_backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    long countByIsActive(boolean isActive);
}

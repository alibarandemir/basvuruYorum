package com.alibarandemir.isin_asli_backend.repository;

import com.alibarandemir.isin_asli_backend.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByEmail(String email);
    Optional<Candidate> findByResetToken(String token);
    long countByEnabled(boolean enabled);
}

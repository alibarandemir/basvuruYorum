package com.alibarandemir.isin_asli_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibarandemir.isin_asli_backend.dto.CandidateUpdateDto;
import com.alibarandemir.isin_asli_backend.dto.ResponseDto;
import com.alibarandemir.isin_asli_backend.entity.Candidate;
import com.alibarandemir.isin_asli_backend.repository.CandidateRepository;

@Service
public class CandidateService{

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public ResponseDto<Candidate> getCandidateProfile(Long id) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidate not found"));
        return ResponseDto.success(candidate,"Kullanıcı profili başarıyla getirildi");
    }

    @Transactional
    public ResponseDto<Candidate> updateProfile(String email, CandidateUpdateDto updateDto) {
        Candidate candidate = candidateRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        // Güncelleme işlemleri
       
        // Diğer alanlar için de benzer kontroller eklenebilir

        Candidate updatedCandidate = candidateRepository.save(candidate);
        return ResponseDto.success(updatedCandidate, "Profil başarıyla güncellendi");
    }

    
}
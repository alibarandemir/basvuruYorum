package com.alibarandemir.isin_asli_backend.service;

import com.alibarandemir.isin_asli_backend.controller.CandidateController;
import com.alibarandemir.isin_asli_backend.dto.CandidateProfileDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibarandemir.isin_asli_backend.dto.CandidateUpdateDto;
import com.alibarandemir.isin_asli_backend.dto.ResponseDto;
import com.alibarandemir.isin_asli_backend.entity.Candidate;
import com.alibarandemir.isin_asli_backend.repository.CandidateRepository;

import java.util.Optional;

@Service
public class CandidateService{

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public ResponseDto<Candidate> getMe(HttpServletRequest req){
        Long candidateId=(Long) req.getAttribute("candidateId");
        if(candidateId==null){
            return ResponseDto.fail("Tekrar giriş yapınız");
        }
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));

        return ResponseDto.success(candidate,"Profil başarıyla getirildi");
    }
    @Transactional
    public ResponseDto<CandidateProfileDto> getCandidateByUserId(HttpServletRequest request,Long id) {
        Long candidateId=(Long)request.getAttribute("candidateId");
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidate not found"));
        boolean isOwnProfile=candidateId!=null&& candidate.getId().equals(candidateId);
        CandidateProfileDto dto= new CandidateProfileDto(candidate.getId(),candidate.getName(),candidate.getSurname(),candidate.getCvFileUrl(),candidate.getPhotoUrl(),isOwnProfile);
        return ResponseDto.success(dto,"Kullanıcı profili başarıyla getirildi");
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
package com.alibarandemir.isin_asli_backend.mapper;

import org.springframework.stereotype.Component;

import com.alibarandemir.isin_asli_backend.dto.FeedbackResponseDto;
import com.alibarandemir.isin_asli_backend.entity.Feedback;
import com.alibarandemir.isin_asli_backend.entity.Candidate;

@Component
public class FeedbackMapper {
    
    public FeedbackResponseDto toDto(Feedback feedback) {
        FeedbackResponseDto dto = new FeedbackResponseDto();
        dto.setId(feedback.getId());
        dto.setTitle(feedback.getTitle());
        dto.setContent(feedback.getContent());
        dto.setPrivate(feedback.isPrivate());
        dto.setInterviewDate(feedback.getInterviewDate());
        dto.setCategory(feedback.getCategory());

        // Eğer feedback private değilse candidate bilgilerini ekle
        if (!feedback.isPrivate()) {
            Candidate candidate = feedback.getCandidate();
            dto.setCandidateId(candidate.getId());
            dto.setCandidateName(candidate.getName());
            dto.setCandidateSurname(candidate.getSurname());
            dto.setCandidatePhoto(candidate.getPhotoUrl());
        }

        return dto;
    }
} 
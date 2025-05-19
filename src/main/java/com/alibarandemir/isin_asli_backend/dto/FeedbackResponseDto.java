package com.alibarandemir.isin_asli_backend.dto;

import java.time.LocalDate;
import com.alibarandemir.isin_asli_backend.entity.FeedbackCategory;
import lombok.Data;

@Data
public class FeedbackResponseDto {
    private Long id;
    private String title;
    private String content;
    private boolean isPrivate;
    private LocalDate interviewDate;
    private FeedbackCategory category;
    
    // Candidate bilgileri
    private Long candidateId;
    private String candidateName;
    private String candidateSurname;
    private String candidatePhoto;
} 
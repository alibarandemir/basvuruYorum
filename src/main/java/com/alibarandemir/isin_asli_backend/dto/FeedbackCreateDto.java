package com.alibarandemir.isin_asli_backend.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alibarandemir.isin_asli_backend.entity.FeedbackCategory;

import lombok.Data;

@Data
public class FeedbackCreateDto {
    private String title;
    private String content;
    private boolean isPrivate;
    private LocalDate interviewDate;
    private FeedbackCategory category;
    private List<MultipartFile> attachments;
    
    
}

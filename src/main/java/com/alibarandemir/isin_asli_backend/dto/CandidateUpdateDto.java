package com.alibarandemir.isin_asli_backend.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CandidateUpdateDto {
    private MultipartFile cvFile;
    private MultipartFile photoFile;
    
}

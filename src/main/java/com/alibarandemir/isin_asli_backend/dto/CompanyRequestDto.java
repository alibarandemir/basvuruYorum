package com.alibarandemir.isin_asli_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyRequestDto {
    @NotBlank(message = "Şirket adı boş olamaz")
    private String name;

    @NotBlank(message = "Şirket açıklaması boş olamaz")
    private String description;
} 
package com.alibarandemir.isin_asli_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateProfileDto {
    private Long id;
    private String name;
    private String surname;
    private String cvFile;
    private String candidatePhoto;
    private boolean isOwnProfile;

}

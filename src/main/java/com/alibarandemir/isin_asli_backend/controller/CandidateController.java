package com.alibarandemir.isin_asli_backend.controller;

import com.alibarandemir.isin_asli_backend.dto.CandidateUpdateDto;
import com.alibarandemir.isin_asli_backend.dto.ResponseDto;
import com.alibarandemir.isin_asli_backend.entity.Candidate;
import com.alibarandemir.isin_asli_backend.service.CandidateService;
import com.alibarandemir.isin_asli_backend.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    private final CandidateService candidateService;
    private final JwtUtil jwtUtil;

    public CandidateController(CandidateService candidateService, JwtUtil jwtUtil) {
        this.candidateService = candidateService;
        this.jwtUtil = jwtUtil;
    }
    @GetMapping("/me")
    public ResponseEntity<ResponseDto<Candidate>> getMe(HttpServletRequest request){
        return ResponseEntity.ok(candidateService.getMe(request));
    }

    @PutMapping("/profile")
    public ResponseEntity<ResponseDto<Candidate>> updateCandidateProfile(
            @RequestBody CandidateUpdateDto candidate,
            HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7); // "Bearer " kısmını kaldır
        String email = jwtUtil.extractUsername(token);
        return ResponseEntity.ok(candidateService.updateProfile(email, candidate));
    }
}

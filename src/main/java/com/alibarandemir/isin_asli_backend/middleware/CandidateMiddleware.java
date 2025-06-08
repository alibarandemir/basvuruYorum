package com.alibarandemir.isin_asli_backend.middleware;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.alibarandemir.isin_asli_backend.security.JwtTokenProvider;
import com.alibarandemir.isin_asli_backend.entity.Candidate;
import com.alibarandemir.isin_asli_backend.repository.CandidateRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CandidateMiddleware implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;
    private final CandidateRepository candidateRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            String email = jwtTokenProvider.getEmailFromToken(token);
            Candidate candidate = candidateRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
            
            // candidate bilgisi requeste eklendi
            request.setAttribute("candidateId", candidate.getId());
        }
        return true;
    }
} 
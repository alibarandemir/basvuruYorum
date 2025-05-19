package com.alibarandemir.isin_asli_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alibarandemir.isin_asli_backend.dto.FeedbackCreateDto;
import com.alibarandemir.isin_asli_backend.dto.FeedbackResponseDto;
import com.alibarandemir.isin_asli_backend.entity.Feedback;
import com.alibarandemir.isin_asli_backend.repository.FeedbackRepository;
import com.alibarandemir.isin_asli_backend.entity.Company;
import com.alibarandemir.isin_asli_backend.entity.Candidate;
import com.alibarandemir.isin_asli_backend.repository.CompanyRepository;
import com.alibarandemir.isin_asli_backend.repository.CandidateRepository;
import com.alibarandemir.isin_asli_backend.mapper.FeedbackMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final CompanyRepository companyRepository;
    private final CandidateRepository candidateRepository;
    private final FeedbackMapper feedbackMapper;

    public List<FeedbackResponseDto> getAllFeedback() {
        return feedbackRepository.findAll().stream()
            .map(feedbackMapper::toDto)
            .collect(Collectors.toList());
    }

    public FeedbackResponseDto createFeedback(FeedbackCreateDto feedbackDto, Long companyId, Long candidateId) {
        Company company = companyRepository.findById(companyId)
            .orElseThrow(() -> new RuntimeException("Company not found"));
            
        Candidate candidate = candidateRepository.findById(candidateId)
            .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Feedback feedback = new Feedback();
        feedback.setTitle(feedbackDto.getTitle());
        feedback.setContent(feedbackDto.getContent());
        feedback.setPrivate(feedbackDto.isPrivate());
        feedback.setInterviewDate(feedbackDto.getInterviewDate());
        feedback.setCategory(feedbackDto.getCategory());
        feedback.setCompany(company);
        feedback.setCandidate(candidate);

        Feedback savedFeedback = feedbackRepository.save(feedback);
        return feedbackMapper.toDto(savedFeedback);
    }
}

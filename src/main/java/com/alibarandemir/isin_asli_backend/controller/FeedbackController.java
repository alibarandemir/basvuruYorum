package com.alibarandemir.isin_asli_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import com.alibarandemir.isin_asli_backend.dto.ResponseDto;
import com.alibarandemir.isin_asli_backend.dto.FeedbackCreateDto;
import com.alibarandemir.isin_asli_backend.dto.FeedbackResponseDto;
import com.alibarandemir.isin_asli_backend.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping()
    public ResponseDto<List<FeedbackResponseDto>> getAllFeedback() {
        return ResponseDto.success(feedbackService.getAllFeedback(), "Tüm geri bildirimler başarıyla getirildi");
    }

    @PostMapping()
    public ResponseDto<FeedbackResponseDto> createFeedback(
            @RequestBody FeedbackCreateDto feedbackDto,
            @RequestParam Long companyId,
            HttpServletRequest request) {
        
        Long candidateId = (Long) request.getAttribute("candidateId");
        return ResponseDto.success(
            feedbackService.createFeedback(feedbackDto, companyId, candidateId),
            "Geri bildirim başarıyla oluşturuldu"
        );
    }
}

package com.alibarandemir.isin_asli_backend.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "feedbacks")
public class Feedback extends BaseEntity {


    @Column(name = "title")
    private String title;

    @Column(name="content",columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_private",nullable = false)
    private boolean isPrivate=false;

    @Column(name = "interview_date")
    private LocalDate interviewDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private FeedbackCategory category;

    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedbackAttachment> attachments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "company_id",nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "candidate_id",nullable = false)
    private Candidate candidate;

    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedbackVote> votes = new ArrayList<>();

    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedbackComment> comments = new ArrayList<>();

}





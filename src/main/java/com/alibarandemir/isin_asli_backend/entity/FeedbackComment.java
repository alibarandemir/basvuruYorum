package com.alibarandemir.isin_asli_backend.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "feedback_comments")
public class FeedbackComment extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate; // Yorumu yapan kullanıcı

    @ManyToOne
    @JoinColumn(name = "feedback_id", nullable = false)
    private Feedback feedback; // Yorum yapılan feedback

    @Column(name = "content",columnDefinition ="TEXT" ,nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private FeedbackComment parentComment;

}

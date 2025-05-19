package com.alibarandemir.isin_asli_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "feedback_votes")
public class FeedbackVote extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "candidate_id",nullable = false)
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "feedback_id",nullable = false)
    private Feedback feedback;

    @Column(name = "vote_type")
    private VoteType voteType;


}

enum VoteType{
    LIKE,
    DISLIKE
}

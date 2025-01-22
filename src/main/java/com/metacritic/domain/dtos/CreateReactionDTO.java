package com.metacritic.domain.dtos;

import com.metacritic.domain.enums.ReactionType;

public class CreateReactionDTO {
    private Long reviewId;

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    private Long commentId;
    private ReactionType type;

}

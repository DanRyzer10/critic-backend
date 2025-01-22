package com.metacritic.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.metacritic.domain.enums.ReactionType;
import jakarta.persistence.*;

@Entity
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "review_id")
    private Review review;

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReactionType type;

    // Getters and Setters
}

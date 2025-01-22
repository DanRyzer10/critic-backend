package com.metacritic.services;


import com.metacritic.domain.Comment;
import com.metacritic.domain.Reaction;
import com.metacritic.domain.Review;
import com.metacritic.domain.dtos.CreateReactionDTO;
import com.metacritic.repositories.ReactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {

    private final ReviewService reviewService;
    private final ReactionRepository reactionRepository;
    private final CommentService commentService;

    public ReactionService(ReviewService reviewService, ReactionRepository reactionRepository, CommentService commentService) {
        this.reviewService = reviewService;
        this.reactionRepository = reactionRepository;
        this.commentService = commentService;
    }

    public List<Reaction>  findReactionByReview(Long reviewId) {
        Review review = reviewService.findById(reviewId);
        if(review == null){
            return null;
        }

        return reactionRepository.findReactionByReview(review);

    }

    public List<Reaction> findReactionByComment(Long commentId){
        Comment comment = commentService.findById(commentId);
        if(comment == null){
            return null;
        }
        return reactionRepository.findReactionByComment(comment);
    }

    public Reaction save(CreateReactionDTO reaction){
        Reaction newReaction = new Reaction();
        newReaction.setType(reaction.getType());
        if(reaction.getReviewId()==null){
            newReaction.setComment(commentService.findById(reaction.getCommentId()));
        }
        if (reaction.getCommentId()==null){
            newReaction.setReview(reviewService.findById(reaction.getReviewId()));
        }
        return reactionRepository.save(newReaction);

    }
}

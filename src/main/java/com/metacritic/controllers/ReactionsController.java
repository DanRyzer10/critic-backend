package com.metacritic.controllers;

import com.metacritic.domain.Review;
import com.metacritic.domain.dtos.CreateReactionDTO;
import com.metacritic.services.ReactionService;
import com.metacritic.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reactions")
public class ReactionsController {

    private final ReviewService reviewService;
    private final ReactionService reactionService;

    public ReactionsController(ReviewService reviewService, ReactionService reactionService) {
        this.reviewService = reviewService;
        this.reactionService = reactionService;
    }

    @GetMapping("/reaction/review/{id}")
    public ResponseEntity<?> GetReactionsByReview(@PathVariable Long id){
        return ResponseEntity.ok(reactionService.findReactionByReview(id));
    }
    @PostMapping("/reaction/create")
    public ResponseEntity<?> CreateReaction(@RequestBody CreateReactionDTO reaction){
        return ResponseEntity.ok(reactionService.save(reaction));
    }
    @GetMapping("/reaction/comment/{id}")
    public ResponseEntity<?> GetReactionsByComment(@PathVariable Long id){
        return ResponseEntity.ok(reactionService.findReactionByComment(id));
    }

}

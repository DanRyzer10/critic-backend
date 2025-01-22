package com.metacritic.controllers;

import com.metacritic.domain.Comment;
import com.metacritic.domain.Review;
import com.metacritic.domain.User;
import com.metacritic.domain.dtos.CreateCommentDTO;
import com.metacritic.services.CommentService;
import com.metacritic.services.ReviewService;
import com.metacritic.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;
    private final ReviewService reviewService;
    private final UserService userService;

    public CommentController(CommentService commentService, ReviewService reviewService, UserService userService) {
        this.commentService = commentService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<?> GetComments(){
        List<Comment> comments = commentService.findAll();
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> GetCommentById(@PathVariable Long id){
        Comment comment = commentService.findById(id);
        if(comment == null){
            return ResponseEntity.badRequest().body("Comment not found");
        }
        return ResponseEntity.ok(comment);
    }
    @PostMapping("/create")
    public ResponseEntity<?> CreateComment(@RequestBody CreateCommentDTO comment){
        Review review = reviewService.findById(comment.getReviewId());
        if(review == null){
            return ResponseEntity.badRequest().body("Review not found");
        }
        User user = userService.findById(comment.getUserId());

        if(user == null){
            return ResponseEntity.badRequest().body("User not found");
        }
        Comment newComment = new Comment();
        newComment.setReview(review);
        newComment.setUser(user);
        newComment.setContent(comment.getContent());
        newComment.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(commentService.save(newComment));
    }
}

package com.metacritic.controllers;

import com.metacritic.domain.Movie;
import com.metacritic.domain.Review;
import com.metacritic.domain.User;
import com.metacritic.domain.dtos.CreateReviewDTO;
import com.metacritic.repositories.ReviewRepository;
import com.metacritic.services.MovieService;
import com.metacritic.services.ReviewService;
import com.metacritic.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;
    private final MovieService movieService;
    private final UserService userService;

    public ReviewController(ReviewRepository reviewRepository, ReviewService reviewService, MovieService movieService, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.reviewService = reviewService;
        this.movieService = movieService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<?> GetReviews(){
        List<Review> reviews = reviewService.findAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/review/movie/{id}")
    public ResponseEntity<?> GetReviewsByMovie(@PathVariable  Long id){
        Movie movie = movieService.findById(id);
        if(movie == null){
            return ResponseEntity.badRequest().body("Movie not found");
        }
        return ResponseEntity.ok(reviewService.findByMovie(movie));
    }
    @GetMapping("/review/user/{id}")
    public ResponseEntity<?> GetReviewsByUser(@PathVariable  Long id){
        User user = userService.findById(id);
        if(user == null){
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(reviewService.findByUser(user));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createReview(@RequestBody CreateReviewDTO req){
        Movie movie = movieService.findById(req.getMovieId());
        if(movie == null){
            return ResponseEntity.badRequest().body("Movie not found");
        }
        User user = userService.findById(req.getUserId());
        if(user == null){
            return ResponseEntity.badRequest().body("User not found");
        }
        Review review = new Review();
        review.setMovie(movie);
        review.setUser(user);
        review.setContent(req.getContent());
        review.setTitle(req.getTitle());
        review.setScore(req.getScore());
        review.setCreatedAt(LocalDateTime.now());
        Review newReview = reviewService.save(review);
        return ResponseEntity.ok(newReview);
    }

}

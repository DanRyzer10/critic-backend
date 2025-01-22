package com.metacritic.services;

import com.metacritic.domain.Movie;
import com.metacritic.domain.Review;
import com.metacritic.domain.User;
import com.metacritic.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findAll() {
        return (List<Review>) reviewRepository.findAll();
    }

    public  Review findById(Long id) {
        return reviewRepository.findReviewById(id);
    }

    public List<Review> findByUser(User user) {
        return reviewRepository.findReviewByUser(user);
    }

    public List<Review> findByMovie(Movie movie) {
        return reviewRepository.findReviewByMovie_Id(movie.getId());
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }
}

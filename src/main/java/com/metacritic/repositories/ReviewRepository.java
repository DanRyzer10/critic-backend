package com.metacritic.repositories;

import com.metacritic.domain.Movie;
import com.metacritic.domain.Review;
import com.metacritic.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository  extends CrudRepository<Review, Long> {
   Review findReviewById(Long id);
    List<Review> findReviewByUser(User user);
    List<Review> findReviewByMovie(Movie movie);
    List<Review> findReviewByMovie_Id(Long id);
}

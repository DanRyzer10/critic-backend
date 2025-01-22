package com.metacritic.repositories;

import com.metacritic.domain.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    Movie findMovieByTitle(String title);
    Movie findMovieById(Long id);
    List<Movie> findMovieByIdIsAfter(Long id);
}

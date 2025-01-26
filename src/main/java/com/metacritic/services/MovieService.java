package com.metacritic.services;

import com.metacritic.domain.Movie;
import com.metacritic.domain.dtos.CreateMovieDTO;
import com.metacritic.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie save(CreateMovieDTO movie) {
        Movie newMovie = new Movie();
        newMovie.setTitle(movie.getTitle());
        newMovie.setDirector(movie.getDirector());
        newMovie.setDescription(movie.getDescription());
        newMovie.setScore(movie.getScore());
        return movieRepository.save(newMovie);
    }

    public List<Movie> findAll() {
        return (List<Movie>) movieRepository.findAll();
    }

    public Movie findById(Long id) {
        return movieRepository.findMovieById(id);
    }
    public Movie findByTitle(String title) {
        return movieRepository.findMovieByTitle(title);
    }
    public List<Movie> findByIdIsAfter(Long id) {
        return movieRepository.findMovieByIdIsAfter(id);
    }


    public List<Movie> findByTitleContaining(String title){
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }
}

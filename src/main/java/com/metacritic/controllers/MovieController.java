package com.metacritic.controllers;

import com.metacritic.domain.Movie;
import com.metacritic.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {


    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("")
    public ResponseEntity<?> GetMovies(){
        List<Movie> movies = movieService.findAll();
        return ResponseEntity.ok(movies);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> GetMovieById(@PathVariable Long id){
        Movie movie = movieService.findById(id);
        if(movie == null){
            return ResponseEntity.badRequest().body("Movie not found");
        }
        return ResponseEntity.ok(movie);
    }
}

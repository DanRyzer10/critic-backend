package com.metacritic.controllers;

import com.metacritic.domain.Movie;
import com.metacritic.domain.dtos.CreateMovieDTO;
import com.metacritic.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {


    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("")
    public ResponseEntity<?> GetMovies(@RequestParam(required = false) String title){
        List<Movie> movies;
        if (title != null && !title.isEmpty()) {
            movies = movieService.findByTitleContaining(title);
        } else {
            movies = movieService.findAll();
        }
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
    @PostMapping("/create")
    public ResponseEntity<?> CreateMovie(@RequestBody CreateMovieDTO movie){
        return ResponseEntity.ok(movieService.save(movie));
    }


}

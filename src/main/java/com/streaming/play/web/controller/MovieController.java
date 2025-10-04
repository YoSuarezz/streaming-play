package com.streaming.play.web.controller;

import com.streaming.play.domain.dto.MovieDto;
import com.streaming.play.domain.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<MovieDto> getMovies() {
        return movieService.getAll();
    }
}

package com.streaming.play.web.controller;

import com.streaming.play.domain.dto.MovieDto;
import com.streaming.play.domain.dto.UpdateMovieDto;
import com.streaming.play.domain.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getMovies() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getById(@PathVariable Long id) {
        MovieDto movieDto = movieService.getById(id);

        if (movieDto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(movieDto);
        }
    }

    @PostMapping
    public ResponseEntity<MovieDto> add(@RequestBody MovieDto movieDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.add(movieDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable long id,@RequestBody UpdateMovieDto updateMovieDto) {

        return ResponseEntity.ok(movieService.update(id, updateMovieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        movieService.deleteById(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }
}

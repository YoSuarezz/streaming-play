package com.streaming.play.web.controller;

import com.streaming.play.domain.dto.MovieDto;
import com.streaming.play.domain.dto.SuggestRequestDto;
import com.streaming.play.domain.dto.UpdateMovieDto;
import com.streaming.play.domain.service.MovieService;
import com.streaming.play.domain.service.StreamingPlayAiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final StreamingPlayAiService aiService;

    public MovieController(MovieService movieService, StreamingPlayAiService aiService) {
        this.movieService = movieService;
        this.aiService = aiService;
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

    @PostMapping("/suggest")
    public ResponseEntity<String> generateMoviesSuggestion(@RequestBody SuggestRequestDto suggestRequestDto) {
        return ResponseEntity.ok(this.aiService.generateMoviesSuggestion(suggestRequestDto.userPreferences()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable long id,@RequestBody UpdateMovieDto updateMovieDto) {

        return ResponseEntity.ok(movieService.update(id, updateMovieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        movieService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

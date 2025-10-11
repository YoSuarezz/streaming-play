package com.streaming.play.web.controller;

import com.streaming.play.domain.dto.MovieDto;
import com.streaming.play.domain.dto.SuggestRequestDto;
import com.streaming.play.domain.dto.UpdateMovieDto;
import com.streaming.play.domain.service.MovieService;
import com.streaming.play.domain.service.StreamingPlayAiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies", description = "Operations about movies of StreamingPlay")
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
    @Operation(
            summary = "Get a movie by its id",
            description = "Returns a single movie that matches the provided id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Movie found"),
                    @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content)
            }
    )
    public ResponseEntity<MovieDto> getById(@Parameter(description = "Id by movie to return", example = "9") @PathVariable long id) {
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
    public ResponseEntity<MovieDto> update(@PathVariable long id,@RequestBody @Valid UpdateMovieDto updateMovieDto) {
        return ResponseEntity.ok(movieService.update(id, updateMovieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        movieService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

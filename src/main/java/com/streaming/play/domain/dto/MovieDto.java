package com.streaming.play.domain.dto;

import com.streaming.play.domain.Genre;

import java.time.LocalDate;

public record MovieDto(
        String title,
        Integer duration,
        Genre genre,
        LocalDate releaseDate,
        Double rating,
        String state
) {
}
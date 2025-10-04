package com.streaming.play.web.controller;

import com.streaming.play.persistence.crud.CrudMovieEntity;
import com.streaming.play.persistence.entity.MovieEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
    private final CrudMovieEntity crudMovieEntity;

    public MovieController(CrudMovieEntity crudMovieEntity) {
        this.crudMovieEntity = crudMovieEntity;
    }

    @GetMapping("/movies")
    public List<MovieEntity> getMovies() {
        return (List<MovieEntity>) crudMovieEntity.findAll();
    }
}

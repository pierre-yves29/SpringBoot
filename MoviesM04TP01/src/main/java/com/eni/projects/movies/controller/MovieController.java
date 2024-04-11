package com.eni.projects.movies.controller;

import com.eni.projects.movies.bll.MovieService;
import org.springframework.stereotype.Controller;

@Controller
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public void displayMovie(int movieId) {
        System.out.println(movieService.getMovieById(movieId));
    }

    public void displayMovies() {
        System.out.println(movieService.getMovies());
    }
}

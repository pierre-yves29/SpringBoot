package com.eni.projects.movies.controller;

import com.eni.projects.movies.bll.MovieService;
import com.eni.projects.movies.bo.Movie;
import com.eni.projects.movies.bo.Participant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/details")
    public String displayMovie(
            @RequestParam(name = "id", required = true) long movieId,
            Model model
    ) {
        Movie movie = movieService.getMovieById(movieId);

        StringBuilder sb = new StringBuilder();

        for (Participant actor: movie.getActors()){
            sb.append(String.format("- %s %s%n", actor.getFirstName(), actor.getLastName()));
        }

        model.addAttribute("movie", movie);
        model.addAttribute("actors", sb.toString());
        return "/movies/details.html";
    }

    @GetMapping
    public String displayMovies(
            Model model
    ) {
        List<Movie> movies = new ArrayList<>();
        movies = movieService.getMovies();

        model.addAttribute("movies", movies);
        return "/movies/list.html";
    }
}

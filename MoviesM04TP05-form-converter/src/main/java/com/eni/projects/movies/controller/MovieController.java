package com.eni.projects.movies.controller;

import com.eni.projects.movies.bll.MovieService;
import com.eni.projects.movies.bo.Genre;
import com.eni.projects.movies.bo.Movie;
import com.eni.projects.movies.bo.Participant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes({"genresSession","memberSession"})
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {

        this.movieService = movieService;
    }

    @GetMapping("/details")
    public String displayMovie(
            @RequestParam(name="id", required=true) int movieId,
            Model model
    ) {
        Movie movie = movieService.getMovieById(movieId);

        StringBuilder stringBuilder = new StringBuilder();

        for(Participant actor: movie.getActors()) {
            stringBuilder
                .append("- ")
                .append(actor.getFirstName())
                .append(" ")
                .append(actor.getLastName())
                .append("\n");
        }

        model.addAttribute("movie", movie);
        model.addAttribute("actors", stringBuilder.toString());
        return "movies/details.html";
    }

    @GetMapping
    public String displayMovies(
            Model model
    ) {
        List<Movie> movies = movieService.getMovies();

        model.addAttribute("movies", movies);
        return "movies/list.html";
    }

    @GetMapping("/create")
    public String createMovie(){
        return "movies/create.html";
    }

    @ModelAttribute("genresSession")
    public List<Genre> getGenres(){
        return movieService.getGenres();
    }
}

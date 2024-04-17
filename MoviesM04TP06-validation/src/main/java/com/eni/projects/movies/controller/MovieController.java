package com.eni.projects.movies.controller;

import com.eni.projects.movies.bll.MovieService;
import com.eni.projects.movies.bo.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/create")
    public String displayForm(
            Model model
    ){
        Movie movie = new Movie();
        Genre genre = new Genre();
        movie.setGenre(genre);

        List<Genre> genres = movieService.getGenres();
        List<Participant> participants = movieService.getParticipants();

        model.addAttribute("movie", movie);
        model.addAttribute("genres", genres);
        model.addAttribute("participants", participants);
        return "movies/create.html";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute("memberSession") Member memberSession,
            @Valid @ModelAttribute("movie") Movie movie,
            BindingResult bindingResult,
            Model model
    ){
        if(memberSession.isAdmin()){
            if(bindingResult.hasErrors()){
                List<Genre> genres = movieService.getGenres();
                List<Participant> participants = movieService.getParticipants();

                model.addAttribute("genres", genres);
                model.addAttribute("participants", participants);

                return "movies/create.html";
            } else {
                movieService.createMovie(movie);
                return "redirect:/movies";
            }
        }

        return "redirect:/contexts";
    }

    @GetMapping
    public String displayMovies(
            Model model
    ) {
        List<Movie> movies = movieService.getMovies();

        model.addAttribute("movies", movies);
        return "movies/list.html";
    }

    @ModelAttribute("genresSession")
    public List<Genre> getGenres(){
        List<Genre> genres = movieService.getGenres();
        return genres;
    }

    @ModelAttribute("memberSession")
    public Member memberSession() {
        return new Member();
    }
}

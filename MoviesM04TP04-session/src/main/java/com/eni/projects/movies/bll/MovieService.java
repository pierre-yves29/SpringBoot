package com.eni.projects.movies.bll;

import com.eni.projects.movies.bo.Genre;
import com.eni.projects.movies.bo.Movie;
import com.eni.projects.movies.bo.Participant;

import java.util.List;

public interface MovieService {
    List<Movie> getMovies();
    Movie getMovieById(long id);
    List<Genre> getGenres();
    Genre getGenreById(long id);
    List<Participant> getParticipants();
    Participant getParticipantById(long id);
    void createMovie(Movie movie);
}

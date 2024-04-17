package com.eni.projects.movies.bll.impl;

import com.eni.projects.movies.bll.MovieService;
import com.eni.projects.movies.bo.Genre;
import com.eni.projects.movies.bo.Movie;
import com.eni.projects.movies.bo.Participant;
import com.eni.projects.movies.bo.Review;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("prod")
public class MovieServiceImpl implements MovieService {
    @Override
    public List<Movie> getMovies() {
        return List.of();
    }

    @Override
    public Movie getMovieById(long id) {
        return null;
    }

    @Override
    public List<Genre> getGenres() {
        return List.of();
    }

    @Override
    public Genre getGenreById(long id) {
        return null;
    }

    @Override
    public List<Participant> getParticipants() {
        return List.of();
    }

    @Override
    public Participant getParticipantById(long id) {
        return null;
    }

    @Override
    public void createMovie(Movie movie) {

    }

    @Override
    public String getMovieTitle(long id) {
        return "";
    }

    @Override
    public void createReview(Review review, long idMovie) {

    }

    @Override
    public List<Review> getReviews(long idMovie) {
        return List.of();
    }
}

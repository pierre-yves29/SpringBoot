package com.eni.projects.movies.bll.impl;

import com.eni.projects.movies.bll.MovieService;
import com.eni.projects.movies.bo.Genre;
import com.eni.projects.movies.bo.Movie;
import com.eni.projects.movies.bo.Participant;
import com.eni.projects.movies.bo.Review;
import com.eni.projects.movies.dal.GenreDAO;
import com.eni.projects.movies.dal.MovieDAO;
import com.eni.projects.movies.dal.ParticipantDAO;
import com.eni.projects.movies.dal.ReviewDAO;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Profile("prod")
@Primary
public class MovieServiceImpl implements MovieService {

    private MovieDAO movieDAO;
    private GenreDAO genreDAO;
    private ParticipantDAO participantDAO;
    private ReviewDAO reviewDAO;

    public MovieServiceImpl(
            MovieDAO movieDAO,
            GenreDAO genreDAO,
            ParticipantDAO participantDAO,
            ReviewDAO reviewDAO
    ){
        this.movieDAO = movieDAO;
        this.genreDAO = genreDAO;
        this.participantDAO = participantDAO;
        this.reviewDAO = reviewDAO;
    }

    @Override
    public List<Movie> getMovies() {
        return movieDAO.findAll();
    }

    @Override
    public Movie getMovieById(long id) {
        return movieDAO.read(id);
    }

    @Override
    public List<Genre> getGenres() {
        return genreDAO.findAll();
    }

    @Override
    public Genre getGenreById(long id) {
        return genreDAO.read(id);
    }

    @Override
    public List<Participant> getParticipants() {
        return participantDAO.findAll();
    }

    @Override
    public Participant getParticipantById(long id) {
        return participantDAO.read(id);
    }

    @Override
    public void createMovie(Movie movie) {
        movieDAO.create(movie);
    }

    @Override
    public String getMovieTitle(long id) {
        return movieDAO.findTitle(id);
    }

    @Override
    public void createReview(Review review, long idMovie) {
        reviewDAO.create(review,idMovie);
    }

    @Override
    public List<Review> getReviews(long idMovie) {
        return reviewDAO.findByFilm(idMovie);
    }
}

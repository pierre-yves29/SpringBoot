package com.eni.projects.movies.dal;

import com.eni.projects.movies.bo.Movie;

import java.util.List;

public interface MovieDAO {
    void create(Movie movie);
    Movie read(long id);
    List<Movie> findAll();
    String findTitle(long id);
}

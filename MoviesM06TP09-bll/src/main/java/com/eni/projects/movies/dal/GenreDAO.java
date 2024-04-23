package com.eni.projects.movies.dal;

import com.eni.projects.movies.bo.Genre;

import java.util.List;

public interface GenreDAO {
    Genre read(long id);
    List<Genre> findAll();
}

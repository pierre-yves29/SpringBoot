package com.eni.projects.movies.dal;

import com.eni.projects.movies.bo.Review;

import java.util.List;

public interface ReviewDAO {
    void create(Review review, long idMovie);
    List<Review> findByFilm(long idMovie);
}

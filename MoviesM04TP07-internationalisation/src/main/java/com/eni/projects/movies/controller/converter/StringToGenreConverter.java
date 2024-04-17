package com.eni.projects.movies.controller.converter;

import com.eni.projects.movies.bll.MovieService;
import com.eni.projects.movies.bo.Genre;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToGenreConverter implements Converter<String, Genre> {
    private MovieService movieService;

    public StringToGenreConverter(
            MovieService movieService
    ){
        this.movieService = movieService;
    }

    @Override
    public Genre convert(String id) {
        long idGenre = Long.parseLong(id);
        return movieService.getGenreById(idGenre);
    }
}

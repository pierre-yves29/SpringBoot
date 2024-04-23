package com.eni.projects.movies.controller.converter;

import com.eni.projects.movies.bll.MovieService;
import com.eni.projects.movies.bo.Participant;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToParticipantConverter implements Converter<String, Participant> {
    private MovieService movieService;

    public StringToParticipantConverter(
            MovieService movieService
    ) {
        this.movieService = movieService;
    }

    @Override
    public Participant convert(String id) {
        long idParticipant = Long.parseLong(id);
        return movieService.getParticipantById(idParticipant);
    }
}

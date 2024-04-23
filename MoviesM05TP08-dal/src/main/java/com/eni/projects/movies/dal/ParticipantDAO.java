package com.eni.projects.movies.dal;

import com.eni.projects.movies.bo.Participant;

import java.util.List;

public interface ParticipantDAO {
    Participant read(long id);
    List<Participant> findAll();
    List<Participant> findActors(long idFilm);
    void createActeur(long idParticipant, long idFilm);
}

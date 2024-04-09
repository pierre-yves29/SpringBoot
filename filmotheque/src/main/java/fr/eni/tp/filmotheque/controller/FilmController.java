package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Film;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmController {

    private FilmService filmService;

    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }

    public void afficherUnFilm(long id){
        System.out.println(filmService.consulterFilmParId(id).toString());
    }

    public void afficherFilms(){
        List<Film> films = filmService.consulterFilms();
        for(Film film : films){
            System.out.println(film.toString());
        }
    }
}

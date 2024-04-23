package com.eni.projects.movies.dal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TestMovieDAO {
   @Autowired
   private MovieDAO movieDAO;

   @Test
    void test_movie_title(){
       long jpId = 1;
       String title = movieDAO.findTitle(jpId);
       assertThat(title).isEqualTo("Jurassic Park");
   }

//    @Test
//    void test_create_movie(){
//        Movie movie = new Movie();
//        movie.setTitle("Interstellar");
//        movie.setYear(2010);
//        movie.setDuration(120);
//        movie.setSynopsis("Un film qui se passedans l'espace");
//
//        Participant kadmerad = new Participant();
//        kadmerad.setId(9);
//
//        movie.setDirector(kadmerad);
//
//        Genre scienceFiction = new Genre();
//        scienceFiction.setId(2);
//
//        movie.setGenre(scienceFiction);
//
//        movieDAO.create(movie);
//
//        System.out.println(movie);
//
//        Movie movieDB = movieDAO.read(movie.getId());
//
////        assertThat(movieDB.getId()).isEqualTo(movie.getId());
//        assertThat(movieDB.getTitle()).isEqualTo(movie.getTitle());
//        assertThat(movieDB.getDuration()).isEqualTo(movie.getDuration());
//        assertThat(movieDB.getYear()).isEqualTo(movie.getYear());
//        assertThat(movieDB.getSynopsis()).isEqualTo(movie.getSynopsis());
//
//    }

//    private Movie findByTitle(String title){
//        String sql = "SELECT * FROM FILM AS f INNER JOIN GENRE AS g ON g.id = f.id_genre INNER JOIN PARTICIPANT AS p ON p.id = f.id_realisateur WHERE f.titre = :title";
//        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//        namedParameters.addValue("title", title);
//
//        Movie movie = namedParameterJdbcTemplate.queryForObject(
//               sql,
//               namedParameters,
//               new MovieRowMapper()
//        );
//        return movie;
//    }


}

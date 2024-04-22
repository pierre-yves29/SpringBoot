package com.eni.projects.movies.dal.impl;

import com.eni.projects.movies.bo.Genre;
import com.eni.projects.movies.bo.Movie;
import com.eni.projects.movies.bo.Participant;
import com.eni.projects.movies.dal.MovieDAO;
import com.eni.projects.movies.dal.ParticipantDAO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MovieDAOImpl implements MovieDAO {

    private static final String SELECT_ALL = "SELECT * FROM FILM AS f INNER JOIN GENRE AS g ON g.id = f.id_genre INNER JOIN PARTICIPANT AS p ON p.id = f.id_realisateur";
    private static final String SELECT_BY_ID = "SELECT * FROM FILM AS f INNER JOIN GENRE AS g ON g.id = f.id_genre INNER JOIN PARTICIPANT AS p ON p.id = f.id_realisateur WHERE f.id = :id";
    private static final String SELECT_TITLE = "SELECT titre FROM film WHERE id = :id";
    private static final String INSERT = "INSERT INTO FILM(titre, annee, duree, synopsis, id_realisateur, id_genre) VALUES(:titre, :annee, :duree, :synopsis, :id_realisateur, :id_genre)";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private ParticipantDAO participantDAO;

    public MovieDAOImpl(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ParticipantDAO participantDAO
    ){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.participantDAO = participantDAO;
    }

    @Override
    public void create(Movie movie) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("titre", movie.getTitle());
        namedParameters.addValue("annee", movie.getYear());
        namedParameters.addValue("duree", movie.getDuration());
        namedParameters.addValue("synopsis", movie.getSynopsis());
        namedParameters.addValue("id_realisateur", movie.getDirector().getId());
        namedParameters.addValue("id_genre", movie.getGenre().getId());

        namedParameterJdbcTemplate.update(
                INSERT,
                namedParameters
        );

        Movie movieDB = findByTitle(movie.getTitle());
        movie.setId(movieDB.getId());

        mapMovieActors(movie);
    }

    private void mapMovieActors(Movie movie){
        System.out.println(movie.getActors());
        for(Participant participant : movie.getActors()){
            participantDAO.createActeur(participant.getId(), movie.getId());
        }
    }

    private Movie findByTitle(String title){
        String sql = "SELECT id FROM film WHERE titre = :title";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("title", title);

        Movie movie = new Movie();
        movie = namedParameterJdbcTemplate.queryForObject(
            sql,
            namedParameters,
            new BeanPropertyRowMapper<>(Movie.class)
        );

        return movie;
    }

    @Override
    public Movie read(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);

        Movie movie =  namedParameterJdbcTemplate.queryForObject(
                SELECT_BY_ID,
                namedParameters,
                new MovieRowMapper()
        );

        movie.setActors(participantDAO.findActors(movie.getId()));

        return movie;
    }

    @Override
    public List<Movie> findAll() {

        List<Movie> movies = jdbcTemplate.query(
                SELECT_ALL,
                new MovieRowMapper()
        );
        return movies;
    }

    @Override
    public String findTitle(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id",id);

        String title = namedParameterJdbcTemplate.queryForObject(
                SELECT_TITLE,
                namedParameters,
                String.class
        );
        return title;
    }

    class MovieRowMapper implements RowMapper<Movie> {

        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {

            Movie movie = new Movie();
            movie.setId(rs.getLong("id"));
            movie.setTitle(rs.getString(2));//titre film
            movie.setYear(rs.getInt("annee"));
            movie.setDuration(rs.getInt("duree"));
            movie.setSynopsis(rs.getString("synopsis"));

            Genre genre = new Genre();
            genre.setId(rs.getLong("id_genre"));
            genre.setTitle(rs.getString(9));//titre genre

            movie.setGenre(genre);

            Participant director = new Participant();
            director.setId(rs.getLong("id_realisateur"));
            director.setFirstName(rs.getString("prenom"));
            director.setLastName(rs.getString("nom"));

            movie.setDirector(director);

            return movie;
        }
    }
}

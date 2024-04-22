package com.eni.projects.movies.dal.impl;

import com.eni.projects.movies.bo.Participant;
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
public class ParticipantDAOImpl implements ParticipantDAO {

    private static final String SELECT_ALL = "SELECT id, nom, prenom FROM  participant";
    private static final String SELECT_BY_ID = "SELECT id, nom, prenom FROM  participant WHERE id = :id";
    private static final String SELECT_ACTORS_BY_FILM = "SELECT p.id, p.nom, p.prenom FROM participant AS p INNER JOIN acteurs AS a ON p.id = a.id_participant WHERE a.id_film = :id_film";
    private static final String INSERT_INTO_ACTORS = "INSERT INTO acteurs(id_film, id_participant) VALUES(:id_film, :id_participant)";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ParticipantDAOImpl(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Participant read(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id",id);

        Participant participant = new Participant();

        participant = namedParameterJdbcTemplate.queryForObject(
            SELECT_BY_ID,
            namedParameters,
            new ParticipantRowMapper()
        );
        return participant;
    }

    @Override
    public List<Participant> findAll() {
        return jdbcTemplate.query(
                SELECT_ALL,
                new ParticipantRowMapper()
        );
    }

    @Override
    public List<Participant> findActors(long idFilm) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id_film", idFilm);

        return namedParameterJdbcTemplate.query(
                SELECT_ACTORS_BY_FILM,
                namedParameters,
                new ParticipantRowMapper()
        );
    }

    @Override
    public void createActeur(long idParticipant, long idFilm) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id_film", idFilm);
        namedParameters.addValue("id_participant", idParticipant);

        namedParameterJdbcTemplate.update(
                INSERT_INTO_ACTORS,
                namedParameters
        );
    }

    class ParticipantRowMapper implements RowMapper<Participant>{

        @Override
        public Participant mapRow(ResultSet rs, int rowNum) throws SQLException {
            Participant participant = new Participant();
            participant.setId(rs.getLong("id"));
            participant.setFirstName(rs.getString("prenom"));
            participant.setLastName(rs.getString("nom"));
            return participant;
        }
    }
}

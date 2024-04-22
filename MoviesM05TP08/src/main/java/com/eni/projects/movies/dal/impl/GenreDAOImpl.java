package com.eni.projects.movies.dal.impl;

import com.eni.projects.movies.bo.Genre;
import com.eni.projects.movies.dal.GenreDAO;
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
public class GenreDAOImpl implements GenreDAO {

    private static final String SELECT_ALL = "SELECT id, titre FROM GENRE";
    private static final String SELECT_BY_ID = "SELECT id, titre FROM GENRE WHERE id = :id";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public GenreDAOImpl(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Genre read(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        Genre genre = namedParameterJdbcTemplate.queryForObject(
                SELECT_BY_ID,
                namedParameters,
                new GenreRowMapper()
        );
        return genre;
    }

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(
                SELECT_ALL,
                new GenreRowMapper()
        );
    }

    private class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {

            Genre genre = new Genre();
            genre.setId(rs.getLong("id"));
            genre.setTitle(rs.getString("titre"));

            return genre;
        }
    }
}

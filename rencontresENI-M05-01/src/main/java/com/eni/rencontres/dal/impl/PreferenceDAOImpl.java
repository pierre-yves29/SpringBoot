package com.eni.rencontres.dal.impl;

import com.eni.rencontres.bo.Preference;
import com.eni.rencontres.dal.PreferenceDAO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PreferenceDAOImpl implements PreferenceDAO {

    private static final String SELECT_ALL = "select id, code, label from preferences";
    private static final String SELECT_BY_ID = "select id, code, label from preferences where id = :id";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PreferenceDAOImpl(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void create(Preference preference) {
        //TO DO
    }

    @Override
    public Preference read(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(
                SELECT_BY_ID,
                namedParameters,
                new BeanPropertyRowMapper<>(Preference.class)
        );
    }

    @Override
    public void update(Preference preference) {
        //TO DO
    }

    @Override
    public void delete(Preference preference) {
        //TO DO
    }

    @Override
    public List<Preference> findAll() {
        return jdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<>(Preference.class));
    }
}

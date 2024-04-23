package com.eni.projects.movies.dal.impl;

import com.eni.projects.movies.bo.Member;
import com.eni.projects.movies.dal.MemberDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {

    private static final String SELECT_ALL = "SELECT id, nom, prenom, email, admin FROM membre";
    private static final String SELECT_BY_ID = "SELECT id, nom, prenom, email, admin FROM membre WHERE id = :id";
    private static final String SELECT_BY_EMAIL = "SELECT id, nom, prenom, email, admin FROM membre WHERE email = :email";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MemberDAOImpl(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Member read(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);

        Member member = namedParameterJdbcTemplate.queryForObject(
                SELECT_BY_ID,
                namedParameters,
                new MemberRowMapper()
        );
        return member;
    }

    @Override
    public Member read(String email) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", email);

        Member member = namedParameterJdbcTemplate.queryForObject(
                SELECT_BY_EMAIL,
                namedParameters,
                new MemberRowMapper()
        );
        return member;
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query(
                SELECT_ALL,
                new MemberRowMapper()
        );
    }

    private class MemberRowMapper implements RowMapper<Member>{

        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setLastName(rs.getString("nom"));
            member.setFirstName(rs.getString("prenom"));
            member.setUsername(rs.getString("email"));
            member.setAdmin(rs.getBoolean("admin"));
            return member;
        }
    }
}

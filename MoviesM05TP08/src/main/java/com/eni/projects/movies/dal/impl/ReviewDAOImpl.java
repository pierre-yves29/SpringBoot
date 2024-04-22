package com.eni.projects.movies.dal.impl;

import com.eni.projects.movies.bo.Member;
import com.eni.projects.movies.bo.Review;
import com.eni.projects.movies.dal.ReviewDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReviewDAOImpl implements ReviewDAO {

    private static final String INSERT = "INSERT INTO avis(note, commentaire, id_membre, id_film) VALUES(:note, :commentaire, :id_membre, :id_film)";
    private static final String SELECT_BY_FILM = "SELECT a.id, a.note, a.commentaire, m.nom, m.prenom FROM avis AS a INNER JOIN membre AS m  ON m.id = a.id_membre WHERE id_film = :id_film";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ReviewDAOImpl(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void create(Review review, long idMovie) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("note", review.getGrade());
        namedParameters.addValue("commentaire", review.getComments());
        namedParameters.addValue("id_membre", review.getMember().getId());
        namedParameters.addValue("id_film", idMovie);

        namedParameterJdbcTemplate.update(
                INSERT,
                namedParameters
        );
    }

    @Override
    public List<Review> findByFilm(long idMovie) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id_film", idMovie);

        return namedParameterJdbcTemplate.query(
                SELECT_BY_FILM,
                namedParameters,
                new ReviewRowMapper()
        );
    }

    class ReviewRowMapper implements RowMapper<Review>{

        @Override
        public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
            Review review = new Review();
            review.setGrade(rs.getInt("note"));
            review.setComments(rs.getString("commentaire"));

            Member member = new Member();
            member.setFirstName(rs.getString("prenom"));
            member.setLastName(rs.getString("nom"));

            review.setMember(member);

            return review;
        }
    }

}

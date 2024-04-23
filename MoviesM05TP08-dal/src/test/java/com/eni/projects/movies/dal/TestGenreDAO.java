package com.eni.projects.movies.dal;

import com.eni.projects.movies.bo.Genre;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestGenreDAO {

    @Autowired
    private GenreDAO genreDAO;

    @Test
    void test_read_genre(){
        long id = 5;
        Genre genre = genreDAO.read(id);
        assertThat(genre.getId()).isEqualTo(5);
        assertThat(genre.getTitle()).isEqualTo("Comédie");

    }

    @Test
    void test_findAll_genres(){
        List<Genre> genres =  genreDAO.findAll();
        assertThat(genres.size()).isEqualTo(8);
        assertThat(genres.get(0).getTitle()).isNotNull();
        assertThat(genres.stream().findFirst().orElse(null).getId()).isEqualTo(1);
        assertThat(genres.stream().findFirst().orElse(null).getTitle()).isEqualTo("Animation");
        assertThat(genres.get(genres.size() - 1).getId()).isEqualTo(8);
        assertThat(genres.get(genres.size() - 1).getTitle()).isEqualTo("Horreur");
    }


}

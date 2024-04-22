package com.eni.rencontres.dal;

import com.eni.rencontres.bo.Person;
import com.eni.rencontres.bo.Preference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestPersonRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @BeforeEach
    void init(){
        String sql = "delete from persons where name='Sophie'";
        jdbcTemplate.update(sql);
    }

    @Test
    void test_01_selectAll(){
        String sql = "SELECT id,name,birthdate,description FROM persons";
        List<Person> persons = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Person.class)
        );
        assertThat(persons.size()).isEqualTo(4);
        assertThat(persons.get(0).getName()).isEqualTo("Arnaud");
    }

    @Test
    void test_02_selectAllPersonGender(){
        String sql = "select pe.name, pe.birthdate, pe.description, pr.code, pr.label from persons as pe inner join preferences as pr on pe.genre_id = pr.id";
        List<Person> persons = jdbcTemplate.query(
                sql,
                new PersonRowMapper()
        );
        assertThat(persons.size()).isEqualTo(4);
        assertThat(persons.get(0).getGender().getLabel()).isEqualTo("Masculin");
    }

    @Test
    void test_03_selectByName(){
        String arnaud = "Arnaud";
        Person person = findByName(arnaud);
        assertThat(person).isNotNull();
        assertThat(person.getName()).isEqualTo(arnaud);
    }

    private Person findByName(String name){
        String sql = "select * from persons where name = :name";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("name", name);

        Person person = namedParameterJdbcTemplate.queryForObject(sql,namedParameters,new BeanPropertyRowMapper<>(Person.class));
        return person;
    }

    @Test
    void test_04_insert(){
        Person sophie = new Person();
        sophie.setName("Sophie");
        sophie.setBirthdate(LocalDate.now());
        sophie.setDescription("Cherche le bonheur.");
        sophie.setLatitude(48f);
        sophie.setLongitude(-4f);

        String sql = "insert into persons(name, birthdate, description, latitude, longitude) values(:name, :birthdate, :description, :latitude, :longitude)";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("name", sophie.getName());
        namedParameters.addValue("birthdate", sophie.getBirthdate());
        namedParameters.addValue("description", sophie.getDescription());
        namedParameters.addValue("latitude", sophie.getLatitude());
        namedParameters.addValue("longitude", sophie.getLongitude());

        namedParameterJdbcTemplate.update(sql, namedParameters);

        Person sophieDB = findByName("Sophie");

        assertThat(sophieDB.getName()).isEqualTo(sophie.getName());
    }

    class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setName(rs.getString(("name")));
            person.setBirthdate(rs.getDate("birthdate").toLocalDate());
            person.setDescription(rs.getString("description"));

            Preference gender = new Preference();
            gender.setCode(rs.getString("code"));
            gender.setLabel(rs.getString("label"));

            person.setGender(gender);

            return person;
        }
    }
}

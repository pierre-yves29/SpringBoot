package com.eni.rencontres.dal.impl;

import com.eni.rencontres.bo.Person;
import com.eni.rencontres.bo.Preference;
import com.eni.rencontres.dal.PersonDAO;
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
public class PersonDAOImpl implements PersonDAO {

    private static final String SELECT_ALL = "SELECT id,name,birthdate,description FROM persons";
    private static final String SELECT_BY_ID = "select pe.name, pe.birthdate, pe.description, pr.code, pr.label from persons as pe inner join preferences as pr on pe.gender_id = pr.id where pe.id = :id";
    private static final String SELECT_ID_BY_NAME = "SELECT id FROM persons WHERE name = :name";
    private static final String INSERT = "insert into persons(name, birthdate, description, latitude, longitude, gender_id) values(:name, :birthdate, :description, :latitude, :longitude, :gender_id)";
    private static final String UPDATE = "update persons set name = :name, description = :description where id = :id";
    private static final String DELETE = "delete from persons where id :id";
    private static final String INSERT_INTO_PERSONS_PREFERENCES = "INSERT INTO persons_preferences(person_id, preference_id) VALUES(:person_id, :preference_id)";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PersonDAOImpl(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = jdbcTemplate.query(
                SELECT_ALL,
                new BeanPropertyRowMapper<>(Person.class)
        );
        return persons;
    }

    private void mapPersonPreferences(Person person){
        for(Preference preference : person.getPreferences()){
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("person_id",person.getId());
            namedParameters.addValue("preference_id",preference.getId());

            namedParameterJdbcTemplate.update(
              INSERT_INTO_PERSONS_PREFERENCES,
              namedParameters
            );
        }
    }

    @Override
    public void create(Person person) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("name", person.getName());
        namedParameters.addValue("birthdate", person.getBirthdate());
        namedParameters.addValue("description", person.getDescription());
        namedParameters.addValue("latitude", person.getLatitude());
        namedParameters.addValue("longitude", person.getLongitude());
        namedParameters.addValue("gender_id",person.getGender().getId());

        namedParameterJdbcTemplate.update(INSERT, namedParameters);

        Person personDB = findByName(person.getName());

        person.setId(personDB.getId());

        mapPersonPreferences(person);
    }

    private Person findByName(String name){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("name", name);

        Person person = namedParameterJdbcTemplate.queryForObject(
                SELECT_ID_BY_NAME,
                namedParameters,
                new BeanPropertyRowMapper<>(Person.class)
        );

        return person;
    }

    @Override
    public Person read(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);

        Person person = namedParameterJdbcTemplate.queryForObject(
                SELECT_BY_ID,
                namedParameters,
                new PersonRowMapper()
        );

        return person;
    }

    @Override
    public void update(Person person) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("name", person.getName());
        namedParameters.addValue("description", person.getDescription());
        namedParameters.addValue("id", person.getId());

        namedParameterJdbcTemplate.update(UPDATE, namedParameters);
    }

    @Override
    public void delete(Person person) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", person.getId());

        namedParameterJdbcTemplate.update(DELETE, namedParameters);
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

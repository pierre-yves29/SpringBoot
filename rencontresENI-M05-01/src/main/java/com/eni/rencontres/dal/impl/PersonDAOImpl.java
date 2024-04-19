package com.eni.rencontres.dal.impl;

import com.eni.rencontres.bll.impl.PersonServiceImpl;
import com.eni.rencontres.bo.Person;
import com.eni.rencontres.dal.PersonRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private JdbcTemplate jdbcTemplate;

    public PersonRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> findAll() {
        String sql = "SELECT id,name,birthdate,description FROM persons";
        List<Person> persons = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Person.class)
        );
        return persons;
    }
}

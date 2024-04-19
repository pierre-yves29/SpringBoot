package com.eni.rencontres.dal;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestPersonRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void test_01_init(){
        jdbcTemplate.queryForList("SELECT * FROM persons");
    }
}

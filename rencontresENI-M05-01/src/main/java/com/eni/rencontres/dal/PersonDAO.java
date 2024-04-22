package com.eni.rencontres.dal;

import com.eni.rencontres.bo.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> findAll();
    void create(Person person);
    Person read(long id);
    void update(Person person);
    void delete(Person person);
}

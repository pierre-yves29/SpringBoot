package com.eni.rencontres.dal;

import com.eni.rencontres.bo.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> findAll();
}

package com.eni.rencontres.bll;

import com.eni.rencontres.bo.Person;

import java.util.List;

public interface PersonService {
    List<Person> getPersons();
    Person getPersonById(long id);
    List<Person> getPersonsByZone(float latitude, float longitude, float radius);
    void create(Person person);
}

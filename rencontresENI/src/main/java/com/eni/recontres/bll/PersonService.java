package com.eni.recontres.bll;

import com.eni.recontres.bo.Person;

import java.util.List;

public interface PersonService {
    List<Person> getPersons();
    Person getPersonById(long id);
    List<Person> getPersonsByZone(float latitude, float longitude, float radius);
}

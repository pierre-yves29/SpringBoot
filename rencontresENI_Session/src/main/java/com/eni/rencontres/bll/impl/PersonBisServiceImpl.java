package com.eni.rencontres.bll.impl;

import com.eni.rencontres.bll.PersonService;
import com.eni.rencontres.bo.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("simon")
public class PersonBisServiceImpl implements PersonService {
    public void display() {
        System.out.println("Profil de Simon");
    }

    @Override
    public List<Person> getPersons() {
        return List.of();
    }

    @Override
    public Person getPersonById(long id) {
        return null;
    }

    @Override
    public List<Person> getPersonsByZone(float latitude, float longitude, float radius) {
        return List.of();
    }
}

package com.eni.recontres.bll.impl;

import com.eni.recontres.bll.PersonService;
import com.eni.recontres.bo.Person;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("simon")
public class PersonBisServiceImpl implements PersonService {
    public void display(){
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
    public List<Person> getPersonsByZone(float latitude, float longitude) {
        return List.of();
    }
}

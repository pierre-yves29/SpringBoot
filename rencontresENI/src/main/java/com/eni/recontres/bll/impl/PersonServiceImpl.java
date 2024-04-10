package com.eni.recontres.bll.impl;

import com.eni.recontres.bll.PersonService;
import com.eni.recontres.bo.Person;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
//@Primary
@Profile("antoine")
public class PersonServiceImpl implements PersonService {

    private List<Person> persons = new ArrayList<>();

    public PersonServiceImpl(){
        Person arnaud = new Person(
                1,
                "Arnaud",
                LocalDate.parse("1998-08-19"),
                "Etalon bigouden. Touche à tout sauf à la coiffe",
                "Masculin",
                new ArrayList<>(),
                1f,
                1f
        );

        Person quentin = new Person(
                2,
                "Quentin",
                LocalDate.parse("1989-01-28"),
                "Aime se déguiser en Batman",
                "Masculin",
                new ArrayList<>(),
                2f,
                2f
        );

        Person clea = new Person(
                3,
                "Cléa",
                LocalDate.parse("1997-06-15"),
                "Fan d'IKEA",
                "Féminin",
                new ArrayList<>(),
                2f,
                2f
        );

        persons.add(arnaud);
        persons.add(quentin);
        persons.add(clea);

    }

    @Override
    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public Person getPersonById(long id) {
        return persons.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
    }

    public List<Person> getPersonsByZone(float latitude, float longitude){
        return persons.stream()
                .filter(person -> person.getLatitude() == latitude && person.getLongitude() == longitude)
                .toList();
    }
}

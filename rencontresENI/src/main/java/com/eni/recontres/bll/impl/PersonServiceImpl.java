package com.eni.recontres.bll.impl;

import com.eni.recontres.bll.PersonService;
import com.eni.recontres.bo.Person;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;
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
                47.958689f,
                -4.365432f
        );

        Person quentin = new Person(
                2,
                "Quentin",
                LocalDate.parse("1989-01-28"),
                "Aime se déguiser en Batman",
                "Masculin",
                new ArrayList<>(),
                47.995537f,
                -4.103790f
        );

        Person clea = new Person(
                3,
                "Cléa",
                LocalDate.parse("1997-06-15"),
                "Fan d'IKEA",
                "Féminin",
                new ArrayList<>(),
                48.099998f,
                -4.33333f
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

    public List<Person> getPersonsByZone(float latitude, float longitude, float radius){

        return persons.stream()
                .filter(person -> isPersonInMyRadius(latitude, longitude, person.getLatitude(), person.getLongitude(),radius))
                .toList();
    }

    private boolean isPersonInMyRadius(
            float myLatitude,
            float myLongitude,
            float personLatitude,
            float personLongitude,
            float radius)
    {
        Geodesic geodesic = Geodesic.WGS84;

        GeodesicData result = geodesic.Inverse(
                myLatitude,
                myLongitude,
                personLatitude,
                personLongitude
        );
        //get a distance in meters
        double distance = result.s12;

        return distance < radius;
    }
}

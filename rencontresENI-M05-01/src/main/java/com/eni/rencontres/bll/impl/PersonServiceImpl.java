package com.eni.rencontres.bll.impl;

import com.eni.rencontres.bll.PersonService;
import com.eni.rencontres.bo.Person;
import com.eni.rencontres.bo.Preference;
import com.eni.rencontres.dal.PersonDAO;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class PersonServiceImpl implements PersonService {

    private PersonDAO personDAO;

    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public List<Person> getPersons() {
        return personDAO.findAll();
    }

    @Override
    public Person getPersonById(long id) {
        return personDAO.read(id);
    }

    @Override
    public List<Person> getPersonsByZone(float latitude, float longitude, float radius) {
        List<Person> persons = getPersons();
        return persons.stream()
            .filter(
                person -> isPersonInMyRadius(latitude, longitude, person.getLatitude(), person.getLongitude(), radius)
            )
            .toList();
    }

    @Override
    public void create(Person person) {
        personDAO.create(person);
    }

    private boolean isPersonInMyRadius(
            float myLatitude,
            float myLongitude,
            float personLatitude,
            float personLongitude,
            float radius
    ) {
        Geodesic geodesic = Geodesic.WGS84;

        GeodesicData result = geodesic.Inverse(
                myLatitude,
                myLongitude,
                personLatitude,
                personLongitude
        );
        // Get distance in meters
        double distance = result.s12;

        return distance < radius;
    }
}

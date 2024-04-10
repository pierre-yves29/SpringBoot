package com.eni.recontres.controller;

import com.eni.recontres.bll.PersonService;
import com.eni.recontres.bo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value="/persons", method= RequestMethod.GET)
    public String displayPersons(
            @RequestParam(name="latitude", required=false) float latitude,
            @RequestParam(name="longitude", required=false) float longitude
    ){
        System.out.println(latitude);
        System.out.println(longitude);
        List<Person> persons = personService.getPersonsByZone(latitude,longitude);
        System.out.println(persons);
        return "/person/list.html";
    }

    @GetMapping("/persons/{id}")
    public String displayPerson(
            @PathVariable(name = "id", required = true) long personId
    ){
        System.out.println(personId);

        Person person = personService.getPersonById(personId);
        System.out.println(person);

        return "person/details.html";
    }
}

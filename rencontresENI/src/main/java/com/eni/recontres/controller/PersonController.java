package com.eni.recontres.controller;

import com.eni.recontres.bll.PersonService;
import com.eni.recontres.bo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value="/persons", method= RequestMethod.GET)
    public String displayPersons(
            @RequestParam(name="latitude", required=false, defaultValue = "0") float latitude,
            @RequestParam(name="longitude", required=false, defaultValue = "0") float longitude,
            @RequestParam(name="radius", required=false, defaultValue = "0") float radius,
            Model model
    ){

        List<Person> persons = new ArrayList<>();

        if ( radius <= 0 ){
            persons = personService.getPersons();
        } else {
            persons = personService.getPersonsByZone(latitude, longitude, radius);
        }

        model.addAttribute("persons", persons);
        return "/person/list.html";
    }

    @GetMapping("/persons/{id}")
    public String displayPerson(
            @PathVariable(name = "id", required = true) long personId,
            Model model
    ){
        Person person = personService.getPersonById(personId);

        if (person == null){
            return "redirect:/persons";
        }

        int age = Period.between(person.getBirthdate(), LocalDate.now()).getYears();

        model.addAttribute("person", person);
        model.addAttribute("age", age);
        return "person/details.html";
    }
}

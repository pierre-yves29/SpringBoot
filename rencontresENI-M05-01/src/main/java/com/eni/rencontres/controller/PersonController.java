package com.eni.rencontres.controller;

import com.eni.rencontres.bll.PersonService;
import com.eni.rencontres.bll.PreferenceService;
import com.eni.rencontres.bo.Person;
import com.eni.rencontres.bo.Preference;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Controller
@SessionAttributes({"preferencesSession"})
public class PersonController {

    private PersonService personService;
    private PreferenceService preferenceService;

    public PersonController(
            PersonService personService,
            PreferenceService preferenceService
    ) {
        this.personService = personService;
        this.preferenceService = preferenceService;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String displayPersons(
            @RequestParam(name = "latitude", required = false, defaultValue = "0") float latitude,
            @RequestParam(name = "longitude", required = false, defaultValue = "0") float longitude,
            @RequestParam(name = "radius", required = false, defaultValue = "0") float radius,
            Model model
    ) {
        List<Person> persons;

        if (radius <= 0) {
            persons = personService.getPersons();
        } else {
            persons = personService.getPersonsByZone(latitude, longitude, radius);
        }

        model.addAttribute("persons", persons);
        return "person/list.html";
    }

    @GetMapping("/persons/{id}")
    public String displayPerson(
            @PathVariable(name = "id", required = true) long personId,
            Model model
    ) {
        Person person = personService.getPersonById(personId);

        if (person == null) {
            return "redirect:/persons";
        }

        System.out.println("Calcul de l'âge");

        int age = Period.between(person.getBirthdate(), LocalDate.now()).getYears();

        model.addAttribute("person", person);
        model.addAttribute("age", age);
        return "person/details.html";
    }

    @GetMapping("/persons/create")
    public String displayForm(
            Model model
    ) {
        Person person = new Person();

        model.addAttribute("person", person);
        return "person/create.html";
    }

    @PostMapping("/persons/create")
    public String createPerson(
            @Valid @ModelAttribute("person") Person person,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "person/create.html";
        } else {
            System.out.println(person);
            personService.create(person);

            return "redirect:/persons";
        }
    }

    @ModelAttribute("preferencesSession")
    public List<Preference> getPreferences() {
        List<Preference> preferences = preferenceService.getPreferences();
        System.out.println(preferences);
        return preferences;
    }
}

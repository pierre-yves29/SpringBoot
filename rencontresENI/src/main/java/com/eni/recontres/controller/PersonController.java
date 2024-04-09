package com.eni.recontres.controller;

import com.eni.recontres.bll.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class PersonController {

    //@Autowired
    private PersonService personService;

    //public PersonController() {}

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    public void displayPerson(){
        personService.display();
    }
}

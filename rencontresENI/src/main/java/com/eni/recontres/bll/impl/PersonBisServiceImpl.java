package com.eni.recontres.bll.impl;

import com.eni.recontres.bll.PersonService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("simon")
public class PersonBisServiceImpl implements PersonService {
    public void display(){
        System.out.println("Profil de Simon");
    }
}

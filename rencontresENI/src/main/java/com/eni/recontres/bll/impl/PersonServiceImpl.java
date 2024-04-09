package com.eni.recontres.bll.impl;

import com.eni.recontres.bll.PersonService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
//@Primary
@Profile("antoine")
public class PersonServiceImpl implements PersonService {
    public void display(){
        System.out.println("Profil d'Antoine");
    }
}

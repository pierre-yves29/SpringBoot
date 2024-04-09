package com.eni.recontres.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class HomeController {

    public HomeController() {
        System.out.println("Home controller");
    }

    public void hello(){
        System.out.println("Hello world !");
    }
}

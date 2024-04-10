package com.eni.recontres.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Scope("prototype")
public class HomeController {

    @GetMapping("/home")
    public String hello(){
        return "home.html";
    }
}

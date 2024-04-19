package com.eni.rencontres;

import com.eni.rencontres.bll.impl.PersonBisServiceImpl;
import com.eni.rencontres.bll.impl.PersonServiceImpl;
import com.eni.rencontres.controller.HomeController;
import com.eni.rencontres.controller.PersonController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RencontresEniApplication {
    public static void main(String[] args) {
        SpringApplication.run(RencontresEniApplication.class, args);
    }
}

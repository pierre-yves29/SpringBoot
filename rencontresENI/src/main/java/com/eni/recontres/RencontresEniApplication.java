package com.eni.recontres;

import com.eni.recontres.bll.impl.PersonBisServiceImpl;
import com.eni.recontres.bll.impl.PersonServiceImpl;
import com.eni.recontres.controller.HomeController;
import com.eni.recontres.controller.PersonController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RencontresEniApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =  SpringApplication.run(RencontresEniApplication.class, args);

//        HomeController homeController = (HomeController) applicationContext.getBean("homeController");
//        homeController.hello();
//        System.out.println(homeController.toString());
//
//        HomeController homeController2 = (HomeController) applicationContext.getBean("homeController");
//        System.out.println(homeController2.toString());
//
//        PersonServiceImpl personServiceImpl = (PersonServiceImpl) applicationContext.getBean("personServiceImpl");
//        personServiceImpl.display();
//
//        PersonBisServiceImpl personBisServiceImpl = (PersonBisServiceImpl) applicationContext.getBean("personBisServiceImpl");
//        personBisServiceImpl.display();

        PersonController personController = applicationContext.getBean(PersonController.class);
        personController.displayPerson();
    }

}

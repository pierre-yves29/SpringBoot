package com.eni.projects.movies;

import com.eni.projects.movies.controller.MovieController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MoviesApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MoviesApplication.class, args);

        MovieController controller = context.getBean(MovieController.class);

        // Affichage du film d'identiant 1
        System.out.println("\nLe film d'identiant 1 est : ");
        controller.displayMovie(1);


        // Affichage de la liste des films
        System.out.println("\n");
        controller.displayMovies();
    }

}

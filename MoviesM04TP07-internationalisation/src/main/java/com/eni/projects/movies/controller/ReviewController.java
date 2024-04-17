package com.eni.projects.movies.controller;

import com.eni.projects.movies.bll.MovieService;
import com.eni.projects.movies.bo.Member;
import com.eni.projects.movies.bo.Movie;
import com.eni.projects.movies.bo.Review;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"memberSession"})
public class ReviewController {

    private MovieService movieService;

    public ReviewController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/reviews/create")
    public String displayForm(
            @ModelAttribute("memberSession") Member memberSession,
            @RequestParam(name="idMovie", required=true) long movieId,
            Model model

    ){
        if (memberSession.getUsername() != null) {
            Review review = new Review();
            Movie movie = movieService.getMovieById(movieId);

            model.addAttribute("review", review);
            model.addAttribute("movie", movie);

            return "/reviews/create.html";
        } else {
            return "redirect:/contexts";
        }
    }

    @PostMapping("/reviews/create")
    public String create(
            @ModelAttribute("memberSession") Member memberSession,
            @ModelAttribute("review") Review review,
            @RequestParam(name="idMovie", required=true) long idMovie
    ){
        Member member = new Member();

        member.setId(memberSession.getId());
        member.setFirstName(memberSession.getFirstName());
        member.setLastName(memberSession.getLastName());
        member.setUsername(memberSession.getUsername());
        member.setAdmin(memberSession.isAdmin());

        review.setMember(member);

        movieService.createReview(review,idMovie);
        return "redirect:/movies";
    }

    @ModelAttribute("memberSession")
    public Member memberSession(){
        return new Member();
    }
}

package com.eni.projects.movies.controller;

import com.eni.projects.movies.bll.ContextService;
import com.eni.projects.movies.bo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"memberSession"})
public class ContextController {
    private ContextService contextService;

    public ContextController(ContextService contextService){
        this.contextService = contextService;
    }

    @GetMapping("contexts/login")
    public String login(
            @ModelAttribute("memberSession") Member memberSession,
            @RequestParam(name= "email", required = false, defaultValue = "jtrillard@campus-eni.fr") String email
    ){
        Member member = contextService.load(email);

        if (member != null) {
            memberSession.setId(member.getId());
            memberSession.setFirstName(member.getFirstName());
            memberSession.setLastName(member.getLastName());
            memberSession.setUsername(member.getUsername());
            memberSession.setAdmin(member.isAdmin());
        }else{
            memberSession.setId(0);
            memberSession.setFirstName(null);
            memberSession.setLastName(null);
            memberSession.setUsername(null);
            memberSession.setAdmin(false);
        }

        return "redirect:/movies";

    }

    @GetMapping("contexts")
    public String getContexts(
            Model model
    ){
        List<Member> members = contextService.findAll();

        model.addAttribute("members", members);
        return "contexts/list.html";
    }

    @GetMapping("contexts/logout")
    public String logout(
            @ModelAttribute("memberSession") Member memberSession
    ){
        memberSession.setId(0);
        memberSession.setFirstName(null);
        memberSession.setLastName(null);
        memberSession.setUsername(null);
        memberSession.setAdmin(false);

        return "redirect:/movies";
    }

    @ModelAttribute("memberSession")
    public Member getMember(){
        return new Member();
    }
}

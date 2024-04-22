package com.eni.projects.movies.bll.impl;

import com.eni.projects.movies.bll.ContextService;
import com.eni.projects.movies.bo.Member;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContextServiceBouchon implements ContextService {
	// Liste des membres
	private static List<Member> lstMembers;

	public ContextServiceBouchon() {
		// initialisation de la liste
		lstMembers = new ArrayList<>();
		lstMembers.add(new Member(1,"Baille", "Anne-Lise", "abaille@campus-eni.fr", "password", false));
		Member admin = new Member(2,"Gobin", "Stéphane", "sgobin@campus-eni.fr", "password", true);
		lstMembers.add(admin);
		lstMembers.add(new Member(3,"Trillard", "Julien", "jtrillard@campus-eni.fr", "password", false));
	}

	@Override
	public Member load(String email) {
		return lstMembers.stream().filter(item -> item.getUsername().equals(email)).findAny().orElse(null);
	}

	@Override
	public List<Member> findAll() {
		return lstMembers;
	}
}

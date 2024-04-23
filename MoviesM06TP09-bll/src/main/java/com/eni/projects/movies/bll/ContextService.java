package com.eni.projects.movies.bll;

import com.eni.projects.movies.bo.Member;

import java.util.List;

public interface ContextService {
    Member load(String email);
    List<Member> findAll();
}

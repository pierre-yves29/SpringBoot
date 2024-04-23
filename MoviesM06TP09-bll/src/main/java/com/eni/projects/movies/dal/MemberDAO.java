package com.eni.projects.movies.dal;

import com.eni.projects.movies.bo.Member;

import java.util.List;

public interface MemberDAO {
    Member read(long id);
    Member read(String email);
    List<Member> findAll();
}

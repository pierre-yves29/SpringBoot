package com.eni.projects.movies.dal;

import com.eni.projects.movies.bo.Member;

public interface MemberDAO {
    Member read(long id);
    Member read(String email);
}

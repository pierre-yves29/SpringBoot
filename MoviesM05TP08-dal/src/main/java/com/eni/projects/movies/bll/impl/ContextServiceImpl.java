package com.eni.projects.movies.bll.impl;

import com.eni.projects.movies.bll.ContextService;
import com.eni.projects.movies.bo.Member;
import com.eni.projects.movies.dal.MemberDAO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ContextServiceImpl implements ContextService {

    private MemberDAO memberDAO;

    public ContextServiceImpl(
            MemberDAO memberDAO
    ){
        this.memberDAO = memberDAO;
    }

    @Override
    public Member load(String email) {
        return memberDAO.read(email);
    }

    @Override
    public List<Member> findAll() {
        return memberDAO.findAll();
    }
}

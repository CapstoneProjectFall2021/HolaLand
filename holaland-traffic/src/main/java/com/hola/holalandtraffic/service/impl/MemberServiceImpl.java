package com.hola.holalandtraffic.service.impl;

import com.hola.holalandtraffic.entity.Member;
import com.hola.holalandtraffic.repository.MemberRepository;
import com.hola.holalandtraffic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    
}

package com.hola.holalandtraffic.service;

import com.hola.holalandtraffic.entity.Member;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MemberService {

    List<Member> getAll() throws DataAccessException;

    Member getOne(int id) throws DataAccessException;

    int save(Member obj) throws DataAccessException;

    boolean update(Member obj) throws DataAccessException;

    boolean delete(int id) throws DataAccessException;
}
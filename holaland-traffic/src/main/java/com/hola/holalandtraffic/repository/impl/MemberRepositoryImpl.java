package com.hola.holalandtraffic.repository.impl;

import com.hola.holalandtraffic.entity.Member;
import com.hola.holalandtraffic.mapper.MemberMapper;
import com.hola.holalandtraffic.repository.IRepositoryQuery;
import com.hola.holalandtraffic.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Member> getAll() throws DataAccessException {
        return jdbcTemplate.query(MEMBER_GET_ALL, new MemberMapper());
    }

    @Override
    public Member getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(MEMBER_GET_ONE, new MemberMapper(), id);
    }

    @Override
    public int save(Member obj) throws DataAccessException {
    }

    @Override
    public boolean update(Member obj) throws DataAccessException {
        return jdbcTemplate.update(
                MEMBER_UPDATE_ONE,
                obj.getMemberName(),
                obj.isMemberGender(),
                obj.getMemberDob(),
                obj.getMemberMobile(),
                obj.getMemberEmail(),
                obj.getMemberRankId(),
                obj.getMemberStatusId(),
                obj.getMemberId()
        ) > 0;
    }

    @Override
    public boolean delete(int id) throws DataAccessException {
        return jdbcTemplate.update(MEMBER_DELETE_ONE, id) > 0;
    }
}
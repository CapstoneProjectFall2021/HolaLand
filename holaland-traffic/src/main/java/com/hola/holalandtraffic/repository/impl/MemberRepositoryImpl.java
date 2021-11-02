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
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(MEMBER_ADD_ONE, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, obj.getMemberName());
            ps.setObject(2, obj.isMemberGender());
            ps.setObject(3, obj.getMemberDob());
            ps.setObject(4, obj.getMemberMobile());
            ps.setObject(5, obj.getMemberEmail());
            ps.setObject(6, obj.getMemberRankId());
            ps.setObject(7, obj.getMemberStatusId());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
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
    public int save(Member obj) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(MEMBER_ADD_ONE, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, obj.getMemberName());
            ps.setObject(2, obj.isMemberGender());
            ps.setObject(3, obj.getMemberDob());
            ps.setObject(4, obj.getMemberMobile());
            ps.setObject(5, obj.getMemberEmail());
            ps.setObject(6, obj.getMemberRankId());
            ps.setObject(7, obj.getMemberStatusId());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }


    @Override
    public boolean delete(int id) throws DataAccessException {
        return jdbcTemplate.update(MEMBER_DELETE_ONE, id) > 0;
    }
}
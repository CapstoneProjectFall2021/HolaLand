package com.hola.holalandcore.repository.impl;

import com.hola.holalandcore.entity.UserDetail;
import com.hola.holalandcore.mapper.UserDetailMapper;
import com.hola.holalandcore.repository.IRepositoryQuery;
import com.hola.holalandcore.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDetailRepositoryImpl implements UserDetailRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDetailRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserDetail> getAllUserBookedByUserId(int id) throws DataAccessException {
        return jdbcTemplate.query(GET_LIST_USER_BOOKED_BY_USER_ID, new UserDetailMapper(), id);
    }

    @Override
    public List<UserDetail> getAllUserAppliedByUserId(int id) throws DataAccessException {
        return jdbcTemplate.query(GET_LIST_USER_APPLIED_BY_USER_ID, new UserDetailMapper(), id);
    }
}

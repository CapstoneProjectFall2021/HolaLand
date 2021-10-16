package com.hola.holalandcore.repository.impl;

import com.hola.holalandcore.entity.User;
import com.hola.holalandcore.mapper.UserMapper;
import com.hola.holalandcore.repository.IRepositoryQuery;
import com.hola.holalandcore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject(GET_USER_BY_USER_EMAIL, new UserMapper(), email);
    }
}

package com.hola.holalandfptu.repository.impl;

import com.hola.holalandfptu.entity.ClubType;
import com.hola.holalandfptu.mapper.ClubTypeMapper;
import com.hola.holalandfptu.repository.ClubTypeRepository;
import com.hola.holalandfptu.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClubTypeRepositoryImpl implements ClubTypeRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClubTypeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ClubType> getAll() throws DataAccessException {
        return jdbcTemplate.query(CLUB_TYPE_GET_ALL, new ClubTypeMapper());
    }

    @Override
    public ClubType getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(CLUB_TYPE_GET_ONE, new ClubTypeMapper(), id);
    }
}

package com.hola.holalandfptu.repository.impl;

import com.hola.holalandfptu.entity.Club;
import com.hola.holalandfptu.mapper.ClubMapper;
import com.hola.holalandfptu.repository.ClubRepository;
import com.hola.holalandfptu.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClubRepositoryImpl implements ClubRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClubRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Club> getAllByType(int typeId) throws DataAccessException {
        return jdbcTemplate.query(CLUB_GET_ALL_BY_TYPE, new ClubMapper(), typeId);
    }

    @Override
    public Club getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(CLUB_GET_ONE, new ClubMapper(), id);
    }

}

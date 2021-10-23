package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.SttWork;
import com.hola.holalandwork.mapper.SttWorkMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.SttWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SttWorkRepositoryImpl implements SttWorkRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SttWorkRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SttWork> getAll() throws DataAccessException {
        return jdbcTemplate.query(STT_WORK_GET_ALL, new SttWorkMapper());
    }

    @Override
    public SttWork getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(STT_WORK_GET_ONE, new SttWorkMapper(), id);
    }
}

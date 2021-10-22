package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.SttWorkRequestFindJob;
import com.hola.holalandwork.mapper.SttWorkRequestFindJobMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.SttWorkRequestFindJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SttWorkRequestFindJobRepositoryImpl implements SttWorkRequestFindJobRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SttWorkRequestFindJobRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SttWorkRequestFindJob> getAll() throws DataAccessException {
        return jdbcTemplate.query(STT_WORK_REQUEST_FIND_JOB_GET_ALL, new SttWorkRequestFindJobMapper());
    }

    @Override
    public SttWorkRequestFindJob getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(STT_WORK_REQUEST_FIND_JOB_GET_ONE, new SttWorkRequestFindJobMapper(), id);
    }
}

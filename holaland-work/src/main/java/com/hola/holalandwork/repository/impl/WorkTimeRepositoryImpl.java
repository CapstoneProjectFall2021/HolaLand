package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkTime;
import com.hola.holalandwork.mapper.WorkTimeMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkTimeRepositoryImpl implements WorkTimeRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkTimeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkTime> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_TIME_GET_ALL, new WorkTimeMapper());
    }

    @Override
    public WorkTime getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_TIME_GET_ONE, new WorkTimeMapper(), id);
    }
}

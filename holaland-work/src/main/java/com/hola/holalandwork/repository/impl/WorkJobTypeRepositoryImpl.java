package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkJobType;
import com.hola.holalandwork.mapper.WorkJobTypeMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkJobTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkJobTypeRepositoryImpl implements WorkJobTypeRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkJobTypeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkJobType> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_JOB_TYPE_GET_ALL, new WorkJobTypeMapper());
    }

    @Override
    public WorkJobType getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_JOB_TYPE_GET_ONE, new WorkJobTypeMapper(), id);
    }
}

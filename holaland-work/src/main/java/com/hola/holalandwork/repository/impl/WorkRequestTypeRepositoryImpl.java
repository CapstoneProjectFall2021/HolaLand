package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkRequestType;
import com.hola.holalandwork.mapper.WorkRequestTypeMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkRequestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkRequestTypeRepositoryImpl implements WorkRequestTypeRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkRequestTypeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkRequestType> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_TYPE_GET_ALL, new WorkRequestTypeMapper());
    }

    @Override
    public WorkRequestType getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REQUEST_TYPE_GET_ONE, new WorkRequestTypeMapper(), id);
    }
}

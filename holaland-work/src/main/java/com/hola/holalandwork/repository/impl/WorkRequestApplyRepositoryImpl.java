package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkRequestApply;
import com.hola.holalandwork.mapper.WorkRequestApplyMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkRequestApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkRequestApplyRepositoryImpl implements WorkRequestApplyRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkRequestApplyRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkRequestApply> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_APPLY_GET_ALL, new WorkRequestApplyMapper());
    }

    @Override
    public WorkRequestApply getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REQUEST_APPLY_GET_ONE, new WorkRequestApplyMapper(), id);
    }
}

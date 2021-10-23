package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkRequestFindJob;
import com.hola.holalandwork.mapper.WorkRequestFindJobMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkRequestFindJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkRequestFindJobRepositoryImpl implements WorkRequestFindJobRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkRequestFindJobRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkRequestFindJob> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_FIND_JOB_GET_ALL, new WorkRequestFindJobMapper());
    }

    @Override
    public List<WorkRequestFindJob> getAllByType(int typeId) throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_FIND_JOB_GET_ALL_BY_TYPE, new WorkRequestFindJobMapper(), typeId);
    }

    @Override
    public WorkRequestFindJob getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REQUEST_FIND_JOB_GET_ONE, new WorkRequestFindJobMapper(), id);
    }

    @Override
    public List<WorkRequestFindJob> getAllSaveDraftByUserId(int id) throws DataAccessException {
        return jdbcTemplate.query(WORK_JOB_SAVE_DRAFT_GET_ALL_BY_USER_ID, new WorkRequestFindJobMapper(), id, 6);
    }

    @Override
    public List<WorkRequestFindJob> getAllPostedByUserId(int id) throws DataAccessException {
        return jdbcTemplate.query(WORK_JOB_POSTED_GET_ALL_BY_USER_ID, new WorkRequestFindJobMapper(), id, 3, 4, 5);
    }
}

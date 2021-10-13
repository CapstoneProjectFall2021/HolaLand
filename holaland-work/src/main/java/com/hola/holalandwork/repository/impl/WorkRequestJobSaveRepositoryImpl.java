package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkRequestJobSave;
import com.hola.holalandwork.mapper.WorkRequestApplyMapper;
import com.hola.holalandwork.mapper.WorkRequestJobSaveMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkRequestJobSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class WorkRequestJobSaveRepositoryImpl implements WorkRequestJobSaveRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkRequestJobSaveRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkRequestJobSave> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_JOBS_SAVE_GET_ALL, new WorkRequestJobSaveMapper());
    }

    @Override
    public WorkRequestJobSave getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REQUEST_JOBS_SAVE_GET_ONE, new WorkRequestJobSaveMapper(), id);
    }
}

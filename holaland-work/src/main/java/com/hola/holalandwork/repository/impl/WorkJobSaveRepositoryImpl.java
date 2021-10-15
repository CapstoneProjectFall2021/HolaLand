package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkJobSave;
import com.hola.holalandwork.mapper.WorkJobSaveMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkJobSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class WorkJobSaveRepositoryImpl implements WorkJobSaveRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkJobSaveRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkJobSave> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_JOBS_SAVE_GET_ALL, new WorkJobSaveMapper());
    }

    @Override
    public WorkJobSave getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_JOBS_SAVE_GET_ONE, new WorkJobSaveMapper(), id);
    }
}

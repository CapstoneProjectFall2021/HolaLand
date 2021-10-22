package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkSalaryType;
import com.hola.holalandwork.mapper.WorkSalaryTypeMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkSalaryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkSalaryTypeRepositoryImpl implements WorkSalaryTypeRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkSalaryTypeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkSalaryType> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_SALARY_TYPE_GET_ALL, new WorkSalaryTypeMapper());
    }

    @Override
    public WorkSalaryType getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_SALARY_TYPE_GET_ONE, new WorkSalaryTypeMapper(), id);
    }
}

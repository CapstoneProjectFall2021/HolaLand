package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkSalaryUnit;
import com.hola.holalandwork.mapper.WorkSalaryUnitMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkSalaryUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkSalaryUnitRepositoryImpl implements WorkSalaryUnitRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkSalaryUnitRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkSalaryUnit> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_SALARY_UNIT_GET_ALL, new WorkSalaryUnitMapper());
    }

    @Override
    public WorkSalaryUnit getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_SALARY_UNIT_GET_ONE, new WorkSalaryUnitMapper(), id);
    }
}

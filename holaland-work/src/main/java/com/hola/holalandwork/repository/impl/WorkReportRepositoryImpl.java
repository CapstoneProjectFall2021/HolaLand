package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkReport;
import com.hola.holalandwork.mapper.WorkReportMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkReportRepositoryImpl implements WorkReportRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkReportRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkReport> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_REPORT_GET_ALL, new WorkReportMapper());
    }

    @Override
    public WorkReport getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REPORT_GET_ONE, new WorkReportMapper(), id);
    }
}

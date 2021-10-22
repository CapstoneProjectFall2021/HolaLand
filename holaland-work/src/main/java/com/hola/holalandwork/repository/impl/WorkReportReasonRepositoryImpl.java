package com.hola.holalandwork.repository.impl;


import com.hola.holalandwork.entity.WorkReportReason;
import com.hola.holalandwork.mapper.WorkReportReasonMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkReportReasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkReportReasonRepositoryImpl implements WorkReportReasonRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkReportReasonRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkReportReason> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_REPORT_REASON_GET_ALL, new WorkReportReasonMapper());
    }

    @Override
    public WorkReportReason getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REPORT_REASON_GET_ONE, new WorkReportReasonMapper(), id);
    }
}

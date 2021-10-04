package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.SttWorkReport;
import com.hola.holalandwork.mapper.SttWorkReportMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.SttWorkReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SttWorkReportRepositoryImpl implements SttWorkReportRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SttWorkReportRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SttWorkReport> getAll() throws DataAccessException {
        return jdbcTemplate.query(STT_WORK_REPORT_GET_ALL, new SttWorkReportMapper());
    }

    @Override
    public SttWorkReport getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(STT_WORK_REPORT_GET_ONE, new SttWorkReportMapper(), id);
    }
}

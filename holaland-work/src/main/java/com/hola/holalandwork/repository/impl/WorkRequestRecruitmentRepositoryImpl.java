package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.mapper.WorkRequestRecruitmentMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkRequestRecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkRequestRecruitmentRepositoryImpl implements WorkRequestRecruitmentRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkRequestRecruitmentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkRequestRecruitment> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_RECRUITMENT_GET_ALL, new WorkRequestRecruitmentMapper());
    }

    @Override
    public List<WorkRequestRecruitment> getAllByType(int typeId, int code) throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_RECRUITMENT_GET_ALL_BY_TYPE, new WorkRequestRecruitmentMapper(), typeId, code);
    }

    @Override
    public WorkRequestRecruitment getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REQUEST_RECRUITMENT_GET_ONE, new WorkRequestRecruitmentMapper(), id);
    }
}

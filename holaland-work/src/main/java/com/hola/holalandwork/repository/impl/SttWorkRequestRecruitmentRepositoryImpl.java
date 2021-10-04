package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.SttWorkRequestRecruitment;
import com.hola.holalandwork.mapper.SttWorkRequestRecruitmentMapper;
import com.hola.holalandwork.mapper.WorkRequestRecruitmentMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.SttWorkRequestRecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SttWorkRequestRecruitmentRepositoryImpl implements SttWorkRequestRecruitmentRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SttWorkRequestRecruitmentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SttWorkRequestRecruitment> getAll() throws DataAccessException {
        return jdbcTemplate.query(STT_WORK_REQUEST_RECRUITMENT_GET_ALL, new SttWorkRequestRecruitmentMapper());
    }

    @Override
    public SttWorkRequestRecruitment getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(STT_WORK_REQUEST_RECRUITMENT_GET_ONE, new SttWorkRequestRecruitmentMapper(), id);
    }
}

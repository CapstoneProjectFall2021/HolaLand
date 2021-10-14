package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.SttWorkRequestApply;
import com.hola.holalandwork.mapper.SttWorkRequestApplyMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.SttWorkRequestApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SttWorkRequestApplyRepositoryImpl implements SttWorkRequestApplyRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SttWorkRequestApplyRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SttWorkRequestApply> getAll() throws DataAccessException {
        return jdbcTemplate.query(STT_WORK_REQUEST_APPLY_GET_ALL, new SttWorkRequestApplyMapper());
    }

    @Override
    public SttWorkRequestApply getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(STT_WORK_REQUEST_APPLY_GET_ONE, new SttWorkRequestApplyMapper(), id);
    }
}

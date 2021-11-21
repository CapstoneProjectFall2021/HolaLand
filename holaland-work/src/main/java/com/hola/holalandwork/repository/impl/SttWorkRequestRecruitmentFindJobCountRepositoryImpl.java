package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.SttWorkRequestRecruitmentFindJobCount;
import com.hola.holalandwork.mapper.SttWorkRequestRecruitmentFindJobCountMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.SttWorkRequestRecruitmentFindJobCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SttWorkRequestRecruitmentFindJobCountRepositoryImpl implements SttWorkRequestRecruitmentFindJobCountRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SttWorkRequestRecruitmentFindJobCountRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SttWorkRequestRecruitmentFindJobCount getOneByUserId(int userId) throws DataAccessException {
        return jdbcTemplate.queryForObject(STT_WORK_REQUEST_RECRUITMENT_FIND_JOB_COUNT_GET_ONE_BY_USER_ID, new SttWorkRequestRecruitmentFindJobCountMapper(), userId);
    }
}

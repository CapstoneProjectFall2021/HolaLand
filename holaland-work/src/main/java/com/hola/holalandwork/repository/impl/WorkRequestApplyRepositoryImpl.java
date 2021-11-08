package com.hola.holalandwork.repository.impl;

import com.hola.holalandcore.entity.UserDetail;
import com.hola.holalandcore.mapper.UserDetailMapper;
import com.hola.holalandwork.entity.WorkRequestApply;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.mapper.WorkRequestApplyMapper;
import com.hola.holalandwork.mapper.WorkRequestRecruitmentMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkRequestApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkRequestApplyRepositoryImpl implements WorkRequestApplyRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkRequestApplyRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkRequestApply> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_APPLY_GET_ALL, new WorkRequestApplyMapper());
    }

    @Override
    public List<WorkRequestRecruitment> getAllByAccountId(int accountId) throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_APPLY_GET_ALL_BY_ACCOUNT_ID, new WorkRequestRecruitmentMapper(), accountId);
    }

    @Override
    public WorkRequestApply getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REQUEST_APPLY_GET_ONE, new WorkRequestApplyMapper(), id);
    }

    @Override
    public List<UserDetail> getAllUserApplied(int id, int status) throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_APPLY_GET_ALL_USER_APPLIED_BY_REQUEST_RECRUITMENT_ID, new UserDetailMapper(), id, status);
    }

    @Override
    public boolean save(WorkRequestApply obj) throws DataAccessException {
        return jdbcTemplate.update(
                INSERT_WORK_REQUEST_APPLY,
                obj.getUserId(),
                obj.getWorkRequestRecruitmentId(),
                obj.getSttWorkCode(),
                obj.isWorkRequestApplyDeleted()
        ) > 0;
    }

    @Override
    public boolean delete(int id) throws DataAccessException {
        return jdbcTemplate.update(DELETE_WORK_REQUEST_APPLY, id) > 0;
    }
}

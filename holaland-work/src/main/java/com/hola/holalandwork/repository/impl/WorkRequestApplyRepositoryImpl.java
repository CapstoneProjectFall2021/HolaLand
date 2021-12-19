package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkRequestApply;
import com.hola.holalandwork.entity.WorkRequestBook;
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
    public List<WorkRequestApply> getAllByRequestId(int id) throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_APPLY_GET_ALL_BY_REQUEST_ID, new WorkRequestApplyMapper(), id);
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
    public boolean updateStatusRequestByUserIdAndRecruitmentId(WorkRequestApply obj) throws DataAccessException {
        return jdbcTemplate.update(
                WORK_REQUEST_APPLY_UPDATE_STT_ONE,
                obj.getSttWorkCode(),
                obj.getUserId(),
                obj.getWorkRequestRecruitmentId()
        ) > 0;
    }

    @Override
    public boolean rejectAllRequestByRecruitmentId(WorkRequestApply obj) throws DataAccessException {
        return jdbcTemplate.update(
                WORK_REQUEST_APPLY_REJECT_STT_ALL,
                3,
                obj.getWorkRequestRecruitmentId()
        ) > 0;
    }

    @Override
    public boolean delete(int id) throws DataAccessException {
        return jdbcTemplate.update(DELETE_WORK_REQUEST_APPLY, id) > 0;
    }
}

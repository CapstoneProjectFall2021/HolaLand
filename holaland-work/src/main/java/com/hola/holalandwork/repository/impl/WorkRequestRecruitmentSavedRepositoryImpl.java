package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.entity.WorkRequestRecruitmentSaved;
import com.hola.holalandwork.mapper.WorkRequestRecruitmentSavedMapper;
import com.hola.holalandwork.mapper.WorkRequestRecruitmentMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkRequestRecruitmentSavedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class WorkRequestRecruitmentSavedRepositoryImpl implements WorkRequestRecruitmentSavedRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkRequestRecruitmentSavedRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkRequestRecruitmentSaved> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_RECRUITMENT_SAVED_GET_ALL, new WorkRequestRecruitmentSavedMapper());
    }

    @Override
    public List<WorkRequestRecruitment> getAllByAccountId(int accountId) throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_RECRUITMENT_SAVED_GET_ALL_BY_ACCOUNT_ID, new WorkRequestRecruitmentMapper(), accountId);
    }

    @Override
    public WorkRequestRecruitmentSaved getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REQUEST_RECRUITMENT_SAVED_GET_ONE, new WorkRequestRecruitmentSavedMapper(), id);
    }

    @Override
    public boolean checkUserSaved(int userId, int recruitmentId) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REQUEST_SAVED_CHECK_EXIST, Boolean.class, userId, recruitmentId);
    }

    @Override
    public boolean save(WorkRequestRecruitmentSaved obj) throws DataAccessException {
        return jdbcTemplate.update(
                INSERT_WORK_REQUEST_SAVED,
                obj.getUserId(),
                obj.getWorkRequestRecruitmentId(),
                obj.isWorkRequestRecruitmentSavedDeleted()
        ) > 0;
    }

    @Override
    public boolean delete(int id) throws DataAccessException {
        return jdbcTemplate.update(DELETE_WORK_REQUEST_SAVE, id) > 0;
    }
}

package com.hola.holalandwork.repository.impl;

import com.hola.holalandwork.entity.WorkRequestFindJob;
import com.hola.holalandwork.mapper.WorkRequestFindJobMapper;
import com.hola.holalandwork.mapper.WorkRequestRecruitmentMapper;
import com.hola.holalandwork.repository.IRepositoryQuery;
import com.hola.holalandwork.repository.WorkRequestFindJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkRequestFindJobRepositoryImpl implements WorkRequestFindJobRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkRequestFindJobRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkRequestFindJob> getAll() throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_FIND_JOB_GET_ALL, new WorkRequestFindJobMapper());
    }

    @Override
    public List<WorkRequestFindJob> getAllByType(int typeId, int code) throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_FIND_JOB_GET_ALL_BY_TYPE, new WorkRequestFindJobMapper(), typeId, code);
    }

    @Override
    public WorkRequestFindJob getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REQUEST_FIND_JOB_GET_ONE, new WorkRequestFindJobMapper(), id);
    }

    @Override
    public List<WorkRequestFindJob> getAllByUserIdAndTypeId(int id, Integer... typeId) throws DataAccessException {
        StringBuilder s = new StringBuilder("(");
        for (int i = 0; i < typeId.length; i++) {
            s.append((i != typeId.length - 1) ? typeId[i] + "," : typeId[i] + ")");
        }
        String sql = "SELECT * FROM work_request_find_job WHERE user_id = ? AND stt_work_code in "
                + s
                + " AND work_request_find_job_deleted = 0";
        return jdbcTemplate.query(sql, new WorkRequestFindJobMapper(), id);
    }

    @Override
    public List<WorkRequestFindJob> getAllListRecruitmentByUserId(int userId, int sttWorkCode) throws DataAccessException {
        return jdbcTemplate.query(WORK_LIST_BOOKED_GET_ALL_BY_USER_ID, new WorkRequestFindJobMapper(), userId, sttWorkCode);
    }

    @Override
    public boolean save(WorkRequestFindJob obj) throws DataAccessException {
        return jdbcTemplate.update(
                INSERT_REQUEST_FIND_JOB,
                obj.getUserId(),
                obj.getSttWorkCode(),
                obj.getWorkRequestTypeId(),
                obj.getWorkSalaryUnitId(),
                obj.getWorkPaymentMethodId(),
                obj.getWorkTimeId(),
                obj.getWorkRequestFindJobExpectedSalary(),
                obj.getWorkRequestFindJobExpectedLocation(),
                obj.getWorkRequestFindJobTitle(),
                obj.getWorkRequestFindJobStartDateTime(),
                obj.getWorkRequestFindJobEndDateTime(),
                obj.getWorkRequestFindJobLastUpdateDateTime(),
                obj.getWorkRequestFindJobDescription(),
                obj.getWorkRequestFindJobPersonalExperience(),
                obj.isWorkRequestFindJobDeleted()
        ) > 0;
    }
}

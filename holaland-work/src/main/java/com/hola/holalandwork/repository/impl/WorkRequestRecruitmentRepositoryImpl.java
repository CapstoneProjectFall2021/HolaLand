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
    public List<WorkRequestRecruitment> getAllByType(int typeId, int sttWorkCode) throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_RECRUITMENT_GET_ALL_BY_TYPE, new WorkRequestRecruitmentMapper(), typeId, sttWorkCode);
    }

    @Override
    public WorkRequestRecruitment getOne(int id) throws DataAccessException {
        return jdbcTemplate.queryForObject(WORK_REQUEST_RECRUITMENT_GET_ONE, new WorkRequestRecruitmentMapper(), id);
    }

    @Override
    public List<WorkRequestRecruitment> getAllByUserIdAndTypeId(int id, Integer... typeId) throws DataAccessException {
        StringBuilder s = new StringBuilder("(");
        for (int i = 0; i < typeId.length; i++) {
            s.append((i != typeId.length - 1) ? typeId[i] + "," : typeId[i] + ")");
        }
        String sql = "SELECT * FROM work_request_recruitment WHERE user_id = ? AND stt_work_code in "
                + s
                + " AND work_request_recruitment_deleted = 0";
        return jdbcTemplate.query(sql, new WorkRequestRecruitmentMapper(), id);
    }

    @Override
    public List<WorkRequestRecruitment> getAllListAppliedByUserId(int userId, int sttWorkCode) throws DataAccessException {
        return jdbcTemplate.query(WORK_LIST_APPLIED_GET_ALL_BY_USER_ID, new WorkRequestRecruitmentMapper(), userId, sttWorkCode);
    }

    @Override
    public List<WorkRequestRecruitment> searchByTitle(String title, int code) throws DataAccessException {
        return jdbcTemplate.query(WORK_REQUEST_RECRUITMENT_SEARCH, new WorkRequestRecruitmentMapper(), "%" + title + "%", code);
    }

    @Override
    public boolean save(WorkRequestRecruitment obj) throws DataAccessException {
        return jdbcTemplate.update(
                INSERT_REQUEST_RECRUITMENT,
                obj.getUserId(),
                obj.getSttWorkCode(),
                obj.getWorkRequestTypeId(),
                obj.getWorkSalaryUnitId(),
                obj.getWorkPaymentMethodId(),
                obj.getWorkRequestRecruitmentTitle(),
                obj.getWorkRequestRecruitmentStartDateTime(),
                obj.getWorkRequestRecruitmentEndDateTime(),
                obj.getWorkRequestRecruitmentLastUpdateDateTime(),
                obj.getWorkRequestRecruitmentDescription(),
                obj.getWorkRequestRecruitmentRequirement(),
                obj.getWorkRequestRecruitmentBenefit(),
                obj.getWorkRequestRecruitmentSalary(),
                obj.getWorkRequestRecruitmentQuantity(),
                obj.isWorkRequestRecruitmentExperienceRequirement(),
                obj.isWorkRequestRecruitmentGenderRequirement(),
                obj.getWorkRequestRecruitmentWorkLocation(),
                obj.isWorkRequestRecruitmentDeleted()
        ) > 0;
    }

    @Override
    public boolean update(WorkRequestRecruitment obj) throws DataAccessException {
        return jdbcTemplate.update(
                WORK_REQUEST_RECRUITMENT_UPDATE_ONE,
                obj.getSttWorkCode(),
                obj.getWorkRequestTypeId(),
                obj.getWorkSalaryUnitId(),
                obj.getWorkPaymentMethodId(),
                obj.getWorkRequestRecruitmentTitle(),
                obj.getWorkRequestRecruitmentEndDateTime(),
                obj.getWorkRequestRecruitmentLastUpdateDateTime(),
                obj.getWorkRequestRecruitmentDescription(),
                obj.getWorkRequestRecruitmentRequirement(),
                obj.getWorkRequestRecruitmentBenefit(),
                obj.getWorkRequestRecruitmentSalary(),
                obj.getWorkRequestRecruitmentQuantity(),
                obj.isWorkRequestRecruitmentExperienceRequirement(),
                obj.isWorkRequestRecruitmentGenderRequirement(),
                obj.getWorkRequestRecruitmentWorkLocation(),
                obj.getWorkRequestRecruitmentNote(),
                obj.getWorkRequestRecruitmentId()
        ) > 0;
    }

    @Override
    public boolean updateSttRequest(WorkRequestRecruitment obj) throws DataAccessException {
        return jdbcTemplate.update(
                WORK_REQUEST_RECRUITMENT_UPDATE_STT_ONE,
                obj.getSttWorkCode(),
                obj.getWorkRequestRecruitmentId()
        ) > 0;
    }

    @Override
    public boolean delete(int id) throws DataAccessException {
        return jdbcTemplate.update(
                WORK_REQUEST_RECRUITMENT_DELETED_ONE,
                id
        ) > 0;
    }
}

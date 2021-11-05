package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkRequestFindJob;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestRecruitmentRepository {

    List<WorkRequestRecruitment> getAll() throws DataAccessException;

    List<WorkRequestRecruitment> getAllByType(int typeId, int sttWorkCode) throws DataAccessException;

    WorkRequestRecruitment getOne(int id) throws DataAccessException;

    List<WorkRequestRecruitment> getAllByUserIdAndTypeId(int id, Integer... typeId) throws DataAccessException;

    List<WorkRequestRecruitment> getAllListAppliedByUserId (int userId, int sttWorkCode) throws DataAccessException;

    boolean save(WorkRequestRecruitment obj) throws DataAccessException;

    boolean delete(int id) throws DataAccessException;
}

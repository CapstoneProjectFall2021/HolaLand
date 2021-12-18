package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkRequestFindJob;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestFindJobRepository {

    List<WorkRequestFindJob> getAll() throws DataAccessException;

    List<WorkRequestFindJob> getAllByType(int typeId, int code) throws DataAccessException;

    WorkRequestFindJob getOne(int id) throws DataAccessException;

    List<WorkRequestFindJob> getAllByUserIdAndTypeId(int id, Integer... typeId) throws DataAccessException;

    List<WorkRequestFindJob> getAllListRecruitmentByUserId (int userId, int sttWorkCode) throws DataAccessException;

    boolean updateSttRequest(WorkRequestFindJob obj) throws DataAccessException;

    boolean save(WorkRequestFindJob obj) throws DataAccessException;

    boolean delete(int id) throws DataAccessException;
}

package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestFindJob;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestFindJobService {

    List<WorkRequestFindJob> getAll() throws DataAccessException;

    WorkRequestFindJob getOne(int id) throws DataAccessException;

    List<WorkRequestFindJob> getAllByType(int typeId, int code) throws DataAccessException;

    List<WorkRequestFindJob> getAllByUserIdAndTypeId(int id, Integer... typeId) throws DataAccessException;

    List<WorkRequestFindJob> getAllListRecruitmentByUserId (int userId, int sttWorkCode) throws DataAccessException;

    List<WorkRequestFindJob> searchByTitle(String title, int code) throws DataAccessException;

    boolean updateSttRequest(WorkRequestFindJob obj) throws DataAccessException;

    boolean save(WorkRequestFindJob obj) throws DataAccessException;

    boolean update(WorkRequestFindJob obj) throws DataAccessException;

    boolean delete(int id) throws DataAccessException;
}

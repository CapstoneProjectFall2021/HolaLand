package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkRequestRecruitmentSaved;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestRecruitmentSavedRepository {

    List<WorkRequestRecruitmentSaved> getAll() throws DataAccessException;

    List<WorkRequestRecruitment> getAllByAccountId(int accountId) throws DataAccessException;

    WorkRequestRecruitmentSaved getOne(int id) throws DataAccessException;

    boolean checkUserSaved(int userId, int recruitmentId) throws DataAccessException;

    boolean save(WorkRequestRecruitmentSaved obj) throws DataAccessException;

    boolean delete(int id) throws DataAccessException;
}

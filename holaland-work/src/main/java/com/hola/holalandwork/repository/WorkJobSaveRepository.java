package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkJobSave;
import com.hola.holalandwork.entity.WorkRequestRecruitment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkJobSaveRepository {

    List<WorkJobSave> getAll() throws DataAccessException;

    List<WorkRequestRecruitment> getAllByAccountId(int accountId) throws DataAccessException;

    WorkJobSave getOne(int id) throws DataAccessException;

}

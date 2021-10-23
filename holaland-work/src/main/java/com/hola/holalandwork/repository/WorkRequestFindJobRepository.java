package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkRequestFindJob;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestFindJobRepository {

    List<WorkRequestFindJob> getAll() throws DataAccessException;

    List<WorkRequestFindJob> getAllByType(int typeId) throws DataAccessException;

    WorkRequestFindJob getOne(int id) throws DataAccessException;

    List<WorkRequestFindJob> getAllByUserIdAndTypeId(int id, Integer... typeId) throws DataAccessException;
}

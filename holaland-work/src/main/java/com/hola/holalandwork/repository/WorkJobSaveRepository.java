package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkJobSave;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkJobSaveRepository {

    List<WorkJobSave> getAll() throws DataAccessException;

    WorkJobSave getOne(int id) throws DataAccessException;

}

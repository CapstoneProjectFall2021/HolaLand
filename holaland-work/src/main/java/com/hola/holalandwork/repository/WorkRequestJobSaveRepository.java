package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkRequestJobSave;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestJobSaveRepository {

    List<WorkRequestJobSave> getAll() throws DataAccessException;

    WorkRequestJobSave getOne(int id) throws DataAccessException;

}

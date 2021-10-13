package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestApply;
import com.hola.holalandwork.entity.WorkRequestJobSave;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestJobSaveService {
    List<WorkRequestJobSave> getAll() throws DataAccessException;

    WorkRequestJobSave getOne(int id) throws DataAccessException;
}

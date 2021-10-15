package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkJobSave;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkJobSaveService {

    List<WorkJobSave> getAll() throws DataAccessException;

    WorkJobSave getOne(int id) throws DataAccessException;
}

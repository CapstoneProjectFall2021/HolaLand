package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkJobType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkJobTypeService {

    List<WorkJobType> getAll() throws DataAccessException;

    WorkJobType getOne(int id) throws DataAccessException;

}

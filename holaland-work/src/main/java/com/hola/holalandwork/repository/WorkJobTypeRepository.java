package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkJobType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkJobTypeRepository {

    List<WorkJobType> getAll() throws DataAccessException;

    WorkJobType getOne(int id) throws DataAccessException;
}

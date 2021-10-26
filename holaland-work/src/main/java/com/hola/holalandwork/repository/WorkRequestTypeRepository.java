package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkRequestType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestTypeRepository {

    List<WorkRequestType> getAll() throws DataAccessException;

    WorkRequestType getOne(int id) throws DataAccessException;
}

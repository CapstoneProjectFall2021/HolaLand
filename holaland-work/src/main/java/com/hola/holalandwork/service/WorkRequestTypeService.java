package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestTypeService {

    List<WorkRequestType> getAll() throws DataAccessException;

    WorkRequestType getOne(int id) throws DataAccessException;

}

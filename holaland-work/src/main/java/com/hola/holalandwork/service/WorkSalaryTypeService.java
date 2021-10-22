package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkSalaryType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkSalaryTypeService {

    List<WorkSalaryType> getAll() throws DataAccessException;

    WorkSalaryType getOne(int id) throws DataAccessException;
}

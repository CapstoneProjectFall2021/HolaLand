package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkSalaryType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkSalaryTypeRepository {

    List<WorkSalaryType> getAll() throws DataAccessException;

    WorkSalaryType getOne(int id) throws DataAccessException;
}

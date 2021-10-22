package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkSalaryUnit;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkSalaryUnitRepository {

    List<WorkSalaryUnit> getAll() throws DataAccessException;

    WorkSalaryUnit getOne(int id) throws DataAccessException;
}

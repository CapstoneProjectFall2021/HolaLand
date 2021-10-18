package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkSalaryUnit;
import com.hola.holalandwork.entity.WorkTime;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkSalaryUnitService {
    List<WorkSalaryUnit> getAll() throws DataAccessException;

    WorkSalaryUnit getOne(int id) throws DataAccessException;
}
